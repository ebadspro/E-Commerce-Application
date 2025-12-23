import { useEffect, useState } from "react"
import { fetchProducts } from "../api/productApi"
import type { Product } from "../api/productApi"
import { data, Link } from "react-router-dom"

export default function Products() {
  const [products, setProducts] = useState<Product[]>([]);

  useEffect(() => {
    fetchProducts()
    .then(data => setProducts(data))
    .catch(err => console.error(err));
  }, []);

  return (
    <>
      <header>ShopX — Products</header>

      <div className="container">
        <div className="grid">
          {products.map(product => (
            <div className="card" key={product.id}>
              <h3>{product.name}</h3>
              <p>{product.description}</p>
              <Link to={`/products/${product.id}`}>
                <button>View</button>
              </Link>
            </div>
          ))}
        </div>
      </div>
    </>
  )
}
