export default function Home() {
  return (
    <>
      <header>
        ShopX - (Your One-Stop E-Commerce Solution)
      </header>

      <div className="container">
        <div className="hero">
          <h1>Premium Products</h1>
          <p>Minimal design. Maximum quality.</p>
          <a href="http://localhost:5173/products"><button style={{ color: 'white' , textDecoration: 'none'  }}>Product Listings</button></a>
          <a href="http://localhost:5173/create"><button style={{ color: 'white' , textDecoration: 'none', padding: '10px 16px', margin: '20px'  }}>Create Product</button></a>
        </div>
      </div>
    </>
  )
}
