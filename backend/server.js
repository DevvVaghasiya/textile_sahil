const express = require('express');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(express.json());

// Endpoint to calculate fabric costs
app.post('/api/calculate', (req, res) => {
    const { warps, wefts, pano, wastage, jobCharge, loomType } = req.body;
    
    let sumOfCosts = 0;
    
    // Warp calculations
    const warpDetails = [];
    if (warps && Array.isArray(warps)) {
        warps.forEach(w => {
            const read = Number(w.read) || 0;
            const denier = Number(w.denier) || 0;
            const p = Number(pano) || 0;
            const rate = Number(w.rate) || 0;
            
            let weight = 0;
            let cost = 0;
            
            if (['water-jet', 'air-jet'].includes(loomType)) {
                weight = (read * denier * 108) / 9000000.0;
                cost = (rate * weight) / 100.0;
            } else {
                weight = (denier * ((read * p) + 112)) / 9000.0;
                cost = (rate * weight) / 1000.0;
            }
            
            sumOfCosts += cost;
            warpDetails.push({ weight, cost });
        });
    }

    // Weft calculations
    const weftDetails = [];
    let sumOfTotalPick = 0;
    if (wefts && Array.isArray(wefts)) {
        wefts.forEach(w => {
            const read = Number(w.pick) || 0; // In android logic, weft1.editView1 is "pick"
            const denier = Number(w.denier) || 0;
            const p = Number(pano) || 0;
            const rate = Number(w.rate) || 0;
            
            let weight = 0;
            let cost = 0;
            
            if (['water-jet', 'air-jet'].includes(loomType)) {
                weight = (read * denier * p * 108) / 9000000.0;
                cost = (rate * weight) / 100.0;
            } else {
                weight = (read * denier * p) / 9000.0;
                cost = (rate * weight) / 1000.0;
            }
            
            sumOfCosts += cost;
            sumOfTotalPick += read;
            weftDetails.push({ weight, cost });
        });
    }

    const wastg = Number(wastage) || 0;
    let yarnCost = 0;
    
    if (['water-jet', 'air-jet'].includes(loomType)) {
        yarnCost = sumOfCosts;
    } else {
        yarnCost = (((3.0 + wastg) * sumOfCosts) / 100.0) + sumOfCosts;
    }
    
    const jobC = Number(jobCharge) || 0;
    const jobCost = (sumOfTotalPick * jobC) / 100.0;
    
    const yarnJob = yarnCost + jobCost;

    res.json({
        warpDetails,
        weftDetails,
        yarnCost,
        jobCost,
        yarnJob,
        sumOfTotalPick
    });
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Backend server running on http://localhost:${PORT}`);
});
