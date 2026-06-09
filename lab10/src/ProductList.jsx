import { useState } from 'react'
import ProductItem from './ProductItem.jsx'

function ProductList({ products }) {
  const [filter, setFilter] = useState('')

  function handleFilterChange(event) {
    setFilter(event.target.value)
  }

  return (
    <div>
      <h1>List of products</h1>
      <label htmlFor="product-filter">Filter by product title: </label>
      <input
        id="product-filter"
        type="text"
        value={filter}
        onChange={handleFilterChange}
      />
      <ul>
        {products
          .filter((product) =>
            product.title.toLowerCase().includes(filter.toLowerCase()),
          )
          .map((product) => (
            <ProductItem
              key={product.id}
              id={product.id}
              title={product.title}
              brand={product.brand}
            />
          ))}
      </ul>
    </div>
  )
}

export default ProductList
