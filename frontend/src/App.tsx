import { useState } from 'react'
import './App.css'

function App() {
  const [result, setResult] = useState('Not connected')
  const [loading, setLoading] = useState(false)

  const checkBackend = async () => {
    try {
      setLoading(true)

      const response = await fetch('/api/v1/health')

      if (!response.ok) {
        throw new Error('Backend request failed')
      }

      const data = await response.json()

      setResult(`${data.application}: ${data.status}`)
    } catch (error) {
      setResult('Could not connect to backend')
      console.error(error)
    } finally {
      setLoading(false)
    }
  }

  return (
    <main>
      <h1>LeadFlow</h1>

      <p>Lead management made simpler.</p>

      <button onClick={checkBackend} disabled={loading}>
        {loading ? 'Checking...' : 'Check Backend Connection'}
      </button>

      <p>Backend status: {result}</p>
    </main>
  )
}

export default App