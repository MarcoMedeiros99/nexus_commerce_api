import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'

import App from './App.jsx'
import HelloWord from './components/HelloWord.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
    <HelloWord />
  </StrictMode>);
