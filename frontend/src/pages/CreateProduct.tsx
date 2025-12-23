import { useState } from "react";
import { createProduct } from "../api/productApi"; 
import type { Product } from "../api/productApi";

const CreateProduct = () => {
  const [product, setProduct] = useState<Omit<Product, "id">>({
    name: "",
    description: "",
    price: 0,
    stock: 0,
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setProduct(prev => ({
      ...prev,
      [name]: name === "price" || name === "stock" ? Number(value) : value
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const res = await createProduct(product);
      alert(`Product created: ${res.name}`);
      setProduct({ name: "", description: "", price: 0, stock: 0 });
    } catch (err) {
      console.error(err);
      alert("Error creating product");
    }
  };

  return (
    <div className="container">
      <div className="card" style={{ maxWidth: "500px", margin: "auto" }}>
        <h2>Create Product</h2>
        <form onSubmit={handleSubmit} className="form">
          <label>Name</label>
          <input type="text" name="name" value={product.name} onChange={handleChange} required />

          <label>Description</label>
          <textarea name="description" value={product.description} onChange={handleChange} />

          <label>Price ($)</label>
          <input type="number" name="price" value={product.price} onChange={handleChange} required />

          <label>Stock</label>
          <input type="number" name="stock" value={product.stock} onChange={handleChange} required />

          <button type="submit">Create</button>
        </form>
      </div>
    </div>
  );
};

export default CreateProduct;
