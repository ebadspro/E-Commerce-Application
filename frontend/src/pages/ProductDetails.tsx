import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchProductById, type Product } from "../api/productApi";

const ProductDetails = () => {
  const { id } = useParams<{ id: string }>();
  const [product, setProduct] = useState<Product | null>(null);

  useEffect(() => {
    if (!id) return;

    fetchProductById(Number(id))
      .then(setProduct)
      .catch(console.error);
  }, [id]);

  if (!product) return <div className="container">Loading...</div>;

  return (
    <div className="container">
      <div className="card">
        <h2>{product.name}</h2>
        <p>{product.description}</p>
        <p>Price: ${product.price}</p>
        <p>Stock: {product.stock}</p>
      </div>
    </div>
  );
};

export default ProductDetails;
