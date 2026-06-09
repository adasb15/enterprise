import { useEffect, useMemo, useState } from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import ProductDetail from './ProductDetail.jsx'
import ProductList from './ProductList.jsx'
import './App.css'

function App() {
  const [products, setProducts] = useState([])

  useEffect(() => {
    const controller = new AbortController()

    async function fetchProducts() {
      const response = await fetch('https://dummyjson.com/products', {
        method: 'GET',
        signal: controller.signal,
      })
      const data = await response.json()

      setProducts(data.products)
    }

    fetchProducts()

    return () => controller.abort()
  }, [])

  const router = useMemo(
    () =>
      createBrowserRouter([
        {
          path: '/',
          element: <ProductList products={products} />,
        },
        {
          path: 'details/:id',
          element: <ProductDetail products={products} />,
        },
      ]),
    [products],
  )

  return <RouterProvider router={router} />
}

export default App
