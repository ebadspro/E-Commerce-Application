import axios from "axios";

export interface Product {
  id: number;
  name: string;
  description?: string;
  price: number;
  stock: number;
}

const BASE_URL = "http://localhost:8080/api/products";

export const fetchProducts = async (): Promise<Product[]> => {
  const res = await axios.get<Product[]>(BASE_URL);
  return res.data;
};

export const fetchProductById = async (id: number): Promise<Product> => {
  const res = await axios.get<Product>(`${BASE_URL}/${id}`);
  return res.data;
};

export const createProduct = async (product: Omit<Product, "id">): Promise<Product> => {
  const res = await axios.post<Product>("http://localhost:8080/api/products", product);
  return res.data;
};

