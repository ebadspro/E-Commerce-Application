import { useState } from "react";
import { useNavigate } from "react-router-dom";

// Types for API response
interface LoginResponse {
  token: string;
  user: {
    id: string;
    username: string;
    role: string;
    name?: string;
  };
  message?: string;
}

interface LoginRequest {
  username: string;
  password: string;
}

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      // API call to your backend running on port 8082
      const response = await fetch("http://localhost:8082/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username,
          password
        } as LoginRequest),
      });

      const data: LoginResponse = await response.json();

      if (!response.ok) {
        throw new Error(data.message || "Login failed. Please check your credentials.");
      }

      // Store token and user data
      localStorage.setItem("token", data.token);
      localStorage.setItem("user", JSON.stringify(data.user));

      // Redirect based on user role from API response
      redirectBasedOnRole(data.user.role);
      
    } catch (err: any) {
      setError(err.message || "An error occurred during login");
      console.error("Login error:", err);
    } finally {
      setLoading(false);
    }
  };

  const redirectBasedOnRole = (role: string) => {
    // Redirect to different pages based on role
    switch (role.toLowerCase()) {
      case "admin":
        navigate("/admin-dashboard");
        break;
      case "manager":
        navigate("/manager-dashboard");
        break;
      case "employee":
        navigate("/employee-dashboard");
        break;
      case "user":
        navigate("/user-dashboard");
        break;
      case "superadmin":
        navigate("/superadmin-dashboard");
        break;
      case "moderator":
        navigate("/moderator-dashboard");
        break;
      default:
        navigate("/dashboard"); // Default fallback
        break;
    }
  };

  return (
    <div className="auth-container">
      <form className="auth-card" onSubmit={handleSubmit}>
        <h2>Login</h2>
        
        {error && (
          <div className="error-message">
            {error}
          </div>
        )}

        <div className="form-group">
          <label htmlFor="username">username</label>
          <input
            id="username"
            type="username"
            placeholder="Enter your username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
            disabled={loading}
            autoComplete="username"
          />
        </div>

        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            id="password"
            type="password"
            placeholder="Enter your password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            disabled={loading}
            autoComplete="current-password"
          />
        </div>

        <button 
          type="submit" 
          disabled={loading}
          className={loading ? "loading" : ""}
        >
          {loading ? (
            <>
              <span className="spinner"></span>
              Logging in...
            </>
          ) : (
            "Login"
          )}
        </button>

        <p className="auth-footer">
          Don't have an account? <a href="/signup">Sign up</a>
        </p>
      </form>
    </div>
  );
};

export default Login;