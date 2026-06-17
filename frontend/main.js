import './style.css';

// Configure API base URL - use environment variable or fallback to deployed backend
const API_BASE_URL = import.meta.env.VITE_API_URL || 'https://textile-sahil-backend.onrender.com';

// Debug: log the API URL being used
console.log('API_BASE_URL:', API_BASE_URL);
console.log('VITE_API_URL env var:', import.meta.env.VITE_API_URL);

let currentCostingMode = 'easy';

document.addEventListener('DOMContentLoaded', () => {
  const warpContainer = document.getElementById('warp-container');
  const weftContainer = document.getElementById('weft-container');
  const addWarpBtn = document.getElementById('add-warp-btn');
  const addWeftBtn = document.getElementById('add-weft-btn');
  const calculateBtn = document.getElementById('calculate-btn');
  const clearBtn = document.getElementById('clear-btn');
  const resultsSection = document.getElementById('results-section');

  const warpTemplate = document.getElementById('warp-template');
  const weftTemplate = document.getElementById('weft-template');

  // Costing Mode Handler
  const modeBtns = document.querySelectorAll('.mode-btn');
  const easyPanel = document.getElementById('easy-mode-panel');
  const fullPanel = document.getElementById('full-mode-panel');
  const calcTitle = document.getElementById('calc-title');
  const calcSubtitle = document.getElementById('calc-subtitle');
  const filterSearchInput = document.querySelector('.filter-search input');
  const loomTypeSelect = document.getElementById('loom-type-select');

  const getWarpLabelText = () => {
    return ['water-jet', 'air-jet'].includes(loomTypeSelect?.value) ? 'Taar' : 'Reed';
  };

  const updateWarpLabels = () => {
    const labelText = getWarpLabelText();
    document.querySelectorAll('.warp-item .input-group:first-child label').forEach(label => {
      label.textContent = labelText;
    });
  };

  const kgGroup = document.getElementById('kg-group');
  const jetParamsSection = document.getElementById('jet-parameters-section');

  const isJetLoom = () => {
    return loomTypeSelect && ['water-jet', 'air-jet'].includes(loomTypeSelect.value);
  };

  const updateModePanels = () => {
    if (currentCostingMode === 'full') {
      easyPanel.classList.add('hidden');
      fullPanel.classList.remove('hidden');
      calcTitle.textContent = 'Full Costing Dashboard';
      calcSubtitle.textContent = 'Detailed expense and cost tracking view';
    } else {
      easyPanel.classList.remove('hidden');
      fullPanel.classList.add('hidden');
      calcTitle.textContent = 'Jacquard Fabric Costing Calculator';
      calcSubtitle.textContent = 'Premium accuracy for your Jacquard fabric costing';
    }
  };

  const updateJetFields = () => {
    if (kgGroup) {
      kgGroup.classList.toggle('hidden', !isJetLoom());
    }
    if (jetParamsSection) {
      jetParamsSection.classList.toggle('hidden', !(currentCostingMode === 'full'));
    }
  };

  const updateModeAndLabels = () => {
    updateModePanels();
    updateWarpLabels();
    updateJetFields();
  };

  modeBtns.forEach(btn => {
    btn.addEventListener('click', () => {
      modeBtns.forEach(b => b.classList.remove('mode-btn-active'));
      btn.classList.add('mode-btn-active');
      currentCostingMode = btn.dataset.mode;
      updateModeAndLabels();
    });
  });

  updateModeAndLabels();

  // Helper to add row
  const addRow = (container, template, type) => {
    const clone = template.content.cloneNode(true);
    const row = clone.querySelector('.item-row');
    
    // Add remove listener
    row.querySelector('.remove-btn').addEventListener('click', () => {
      row.remove();
    });
    
    container.appendChild(clone);
    if (type === 'warp') updateWarpLabels();
  };

  // Add initial rows (2 warp, 4 weft as per original app defaults approx)
  addRow(warpContainer, warpTemplate, 'warp');
  addRow(weftContainer, weftTemplate, 'weft');

  addWarpBtn.addEventListener('click', () => addRow(warpContainer, warpTemplate, 'warp'));
  addWeftBtn.addEventListener('click', () => addRow(weftContainer, weftTemplate, 'weft'));

  // Calculate
  calculateBtn.addEventListener('click', async () => {
    // Gather Warps
    const warps = [];
    document.querySelectorAll('.warp-item').forEach(el => {
      warps.push({
        read: el.querySelector('.w-read').value,
        denier: el.querySelector('.w-denier').value,
        rate: el.querySelector('.w-rate').value
      });
    });

    // Gather Wefts
    const wefts = [];
    document.querySelectorAll('.weft-item').forEach(el => {
      wefts.push({
        pick: el.querySelector('.we-pick').value,
        denier: el.querySelector('.we-denier').value,
        rate: el.querySelector('.we-rate').value
      });
    });

    const pano = document.getElementById('pano').value;
    const wastage = document.getElementById('wastage').value;
    const jobCharge = document.getElementById('jobCharge').value;

    const payload = {
      warps,
      wefts,
      pano,
      wastage,
      jobCharge,
      loomType: loomTypeSelect ? loomTypeSelect.value : 'jacquard'
    };

    let totalKg = 0;
    if (loomTypeSelect && ['water-jet', 'air-jet'].includes(loomTypeSelect.value)) {
      const p = Number(pano) || 0;
      warps.forEach(w => {
        const taar = Number(w.read) || 0;
        const denier = Number(w.denier) || 0;
        totalKg += (taar * denier * 108) / 9000000;
      });
      wefts.forEach(w => {
        const pick = Number(w.pick) || 0;
        const denier = Number(w.denier) || 0;
        totalKg += (pick * denier * p * 108) / 9000000;
      });
    }

    try {
      const response = await fetch(`${API_BASE_URL}/api/calculate`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      if (!response.ok) {
        throw new Error(`Server error: ${response.status} ${response.statusText}`);
      }

      const data = await response.json();
      
      const meterMultiplier = Number(document.getElementById('meter').value) || 1;
      
      // Update UI for 1 Meter
      document.getElementById('res-yarn-cost-1').innerText = Number(data.yarnCost).toFixed(2);
      document.getElementById('res-job-cost-1').innerText = Number(data.jobCost).toFixed(2);
      document.getElementById('res-total-1').innerText = Number(data.yarnJob).toFixed(2);

      // Update UI for N Meters
      document.getElementById('res-yarn-cost-n').innerText = (Number(data.yarnCost) * meterMultiplier).toFixed(2);
      document.getElementById('res-job-cost-n').innerText = (Number(data.jobCost) * meterMultiplier).toFixed(2);
      document.getElementById('res-total-n').innerText = (Number(data.yarnJob) * meterMultiplier).toFixed(2);
      
      const resKgItem1 = document.getElementById('res-kg-item-1');
      const resKgItemN = document.getElementById('res-kg-item-n');
      if (loomTypeSelect && ['water-jet', 'air-jet'].includes(loomTypeSelect.value)) {
        if (resKgItem1) resKgItem1.style.display = 'flex';
        if (resKgItemN) resKgItemN.style.display = 'flex';
        document.getElementById('res-kg-1').innerText = totalKg.toFixed(4);
        document.getElementById('res-kg-n').innerText = (totalKg * meterMultiplier).toFixed(4);
      } else {
        if (resKgItem1) resKgItem1.style.display = 'none';
        if (resKgItemN) resKgItemN.style.display = 'none';
      }

      document.getElementById('results-n-title').innerText = `For ${meterMultiplier} Meter${meterMultiplier !== 1 ? 's' : ''}`;
      
      resultsSection.classList.remove('hidden');
      
      // Small animation
      resultsSection.style.transform = 'scale(0.95)';
      setTimeout(() => {
        resultsSection.style.transform = 'scale(1)';
        resultsSection.style.transition = 'transform 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275)';
      }, 50);

    } catch (err) {
      console.error('API Error:', err);
      console.error('API_BASE_URL:', API_BASE_URL);
      alert(`Error connecting to calculation server: ${err.message}\n\nBackend URL: ${API_BASE_URL}`);
    }
  });

  // Clear
  clearBtn.addEventListener('click', () => {
    document.querySelectorAll('input').forEach(input => input.value = '');
    resultsSection.classList.add('hidden');
  });

  // Handle Loom Type Selection
  const loomTypes = {
    'jacquard': {
      title: 'Jacquard Fabric Costing Calculator',
      subtitle: 'Premium accuracy for your Jacquard fabric costing'
    },
    'air-jet': {
      title: 'Air Jet Fabric Costing Calculator',
      subtitle: 'Premium accuracy for your Air Jet fabric costing'
    },
    'water-jet': {
      title: 'Water Jet Fabric Costing Calculator',
      subtitle: 'Premium accuracy for your Water Jet fabric costing'
    }
  };

  if (loomTypeSelect) {
    loomTypeSelect.addEventListener('change', (e) => {
      const selectedType = e.target.value;
      if (loomTypes[selectedType]) {
        const currentMode = document.querySelector('.mode-btn-active').dataset.mode;
        if (currentMode === 'full') {
          calcTitle.textContent = 'Full Costing Dashboard';
          calcSubtitle.textContent = 'Detailed expense and cost tracking view';
        } else {
          calcTitle.textContent = loomTypes[selectedType].title;
          calcSubtitle.textContent = loomTypes[selectedType].subtitle;
        }
        updateWarpLabels();
        updateJetFields();
      }
    });
  }

  // Khata Expense calculation
  const khataInputs = [
    'khata-light', 'khata-karigar', 'khata-helper', 'khata-master',
    'khata-supervisor', 'khata-tempo', 'khata-watchman',
    'khata-maintenance', 'khata-extra', 'khata-emi'
  ];
  
  const calculateKhata = () => {
    let total = 0;
    khataInputs.forEach(id => {
      const el = document.getElementById(id);
      if (el) total += Number(el.value) || 0;
    });
    const totalEl = document.getElementById('khata-total');
    if (totalEl) totalEl.value = total;
    if (typeof calculateProperCosting === 'function') calculateProperCosting();
  };
  
  khataInputs.forEach(id => {
    const el = document.getElementById(id);
    if (el) el.addEventListener('input', calculateKhata);
  });

  // Production Calculation (Saree)
  const prodInputs = ['prod-rpm', 'prod-eff', 'prod-pana', 'prod-pick', 'prod-mtr', 'prod-days', 'prod-loom'];
  const calculateProd = () => {
      const rpm = Number(document.getElementById('prod-rpm').value) || 0;
      const eff = Number(document.getElementById('prod-eff').value) || 0;
      const pana = Number(document.getElementById('prod-pana').value) || 0;
      const pick = Number(document.getElementById('prod-pick').value) || 0;
      const mtr = Number(document.getElementById('prod-mtr').value) || 0;
      const days = Number(document.getElementById('prod-days')?.value) || 0;
      const loom = Number(document.getElementById('prod-loom')?.value) || 0;
      
      let saree = 0;
      if (pick > 0 && mtr > 0) {
        saree = ((rpm * eff * 60 * 24) / (40 * pick * 100)) * (pana / mtr);
      }
      
      const sareeEl = document.getElementById('prod-saree');
      if (sareeEl) sareeEl.value = Math.floor(saree);
      
      const totalMonth = saree * days * loom;
      const monthEl = document.getElementById('prod-month');
      if (monthEl) monthEl.value = Math.floor(totalMonth);
  };
  
  prodInputs.forEach(id => {
    const el = document.getElementById(id);
    if (el) el.addEventListener('input', calculateProd);
  });
  
  if (loomTypeSelect) {
    loomTypeSelect.addEventListener('change', calculateProd);
  }

  // Proper Costing calculation
  const properInputs = ['proper-product-costing', 'proper-selling-price', 'proper-monthly-prod'];
  const calculateProperCosting = () => {
    const productCosting = Number(document.getElementById('proper-product-costing')?.value) || 0;
    const sellingPrice = Number(document.getElementById('proper-selling-price')?.value) || 0;
    const monthlyProd = Number(document.getElementById('proper-monthly-prod')?.value) || 0;
    const khataTotal = Number(document.getElementById('khata-total')?.value) || 0;

    let unitPrice = productCosting;
    if (monthlyProd > 0) {
      unitPrice += (khataTotal / monthlyProd);
    }
    
    const unitPriceEl = document.getElementById('proper-unit-price');
    if (unitPriceEl) unitPriceEl.value = unitPrice.toFixed(2);

    const profit = sellingPrice - unitPrice;
    const profitEl = document.getElementById('proper-profit');
    if (profitEl) profitEl.value = profit.toFixed(2);

    const totalProfit = monthlyProd * profit;
    const totalProfitEl = document.getElementById('proper-month-total-profit');
    if (totalProfitEl) totalProfitEl.value = totalProfit.toFixed(2);
  };

  properInputs.forEach(id => {
    const el = document.getElementById(id);
    if (el) el.addEventListener('input', calculateProperCosting);
  });

});
