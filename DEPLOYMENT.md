# Deployment Guide

## Problem
The frontend (deployed on Vercel) was hardcoded to call `http://localhost:3000/api/calculate`, which doesn't work from the cloud. The error "Error connecting to calculation server" occurred because the backend URL was unreachable.

## Solution
The frontend now uses a configurable API URL via the `VITE_API_URL` environment variable.

## Local Development

1. Start the backend server:
```bash
cd backend
npm install
npm start
# Backend will run on http://localhost:3000
```

2. Start the frontend dev server:
```bash
cd frontend
npm install
npm run dev
# Frontend will run on http://localhost:5173
# It will automatically connect to http://localhost:3000
```

3. The frontend will automatically use the `VITE_API_URL` from `.env.local` (set to `http://localhost:3000`)

## Production Deployment

### Option 1: Deploy Backend to Vercel (Recommended)

1. **Create a Vercel project for the backend:**
   ```bash
   cd backend
   npm install -g vercel
   vercel
   ```

2. **Get the backend URL** from Vercel deployment (e.g., `https://textile-calc-backend.vercel.app`)

3. **Update frontend environment variable in Vercel:**
   - Go to your frontend project in [Vercel Dashboard](https://vercel.com/dashboard)
   - Settings → Environment Variables
   - Add: `VITE_API_URL=https://textile-calc-backend.vercel.app`
   - Redeploy frontend

4. **Verify backend CORS is enabled:**
   The backend (`backend/server.js`) already has CORS enabled:
   ```javascript
   app.use(cors());
   ```

### Option 2: Deploy Backend to Alternative Service

- **Heroku**: `heroku create` and deploy
- **Railway**: Connect GitHub repo and deploy
- **AWS Lambda**: Use Serverless Framework
- **Docker on any cloud**: Build container and deploy

Then set `VITE_API_URL` to your backend URL in Vercel environment variables.

### Option 3: Create a Vercel Serverless Function Proxy

Create `api/calculate.js` in the frontend's Vercel `/api` folder to forward requests:

```javascript
// api/calculate.js
export default async function handler(req, res) {
  const BACKEND_URL = process.env.BACKEND_URL || 'http://localhost:3000';
  
  const response = await fetch(`${BACKEND_URL}/api/calculate`, {
    method: req.method,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(req.body)
  });
  
  const data = await response.json();
  res.status(response.status).json(data);
}
```

Then set `VITE_API_URL=https://your-frontend.vercel.app/api` in Vercel.

## Verification Steps

1. **Local**: Backend at `http://localhost:3000` and frontend at `http://localhost:5173` should work together
2. **Production**: Enter sample weft/warp parameters and click "Calculate" — should see results without error

## Environment Variables Reference

- `VITE_API_URL`: Full URL to backend API (used by frontend)
- `BACKEND_URL`: Backend base URL (used by Vercel proxy function, if using Option 3)

## Troubleshooting

**Error: "Error connecting to calculation server"**
- Check that `VITE_API_URL` is set correctly in Vercel Environment Variables
- Verify the backend service is running and accessible from the frontend's IP
- Check CORS headers in backend are correct

**Frontend shows old cached code**
- Clear browser cache (Ctrl+Shift+Delete or Cmd+Shift+Delete)
- Redeploy frontend: `vercel --prod`

**Backend not receiving requests**
- Check that frontend is using correct API URL via browser DevTools → Network
- Verify backend server is running: `curl http://localhost:3000` or visit in browser
