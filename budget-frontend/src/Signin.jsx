import { useState } from "react";
import api from "./services/api";

function Signin({ onSigninSuccess, onShowSignup }) {
  const [form, setForm] = useState({
    email: "",
    password: "",
  });

  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!form.email.trim() || !form.password) {
      alert("Please enter your email and password.");
      return;
    }

    try {
      setLoading(true);

      const response = await api.post("/auth/signin", {
        email: form.email.trim(),
        password: form.password,
      });

      alert(`Welcome back, ${response.data.name}!`);

      if (onSigninSuccess) {
        onSigninSuccess(response.data);
      }
    } catch (error) {
      console.error("Signin error:", error);

      alert(
        error.response?.data?.message ||
        "Sign in failed. Please check your email and password."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-card">

        <div className="auth-header">
          <h1>Budget Planner</h1>
          <h2>Welcome Back</h2>
          <p>Sign in to manage your finances.</p>
        </div>

        <form onSubmit={handleSubmit}>

          <label>Email</label>
          <input
            type="email"
            placeholder="Enter your email"
            value={form.email}
            onChange={(e) =>
              setForm({
                ...form,
                email: e.target.value,
              })
            }
          />

          <label>Password</label>
          <input
            type="password"
            placeholder="Enter your password"
            value={form.password}
            onChange={(e) =>
              setForm({
                ...form,
                password: e.target.value,
              })
            }
          />

          <button
            type="submit"
            className="primary-button auth-button"
            disabled={loading}
          >
            {loading ? "Signing In..." : "Sign In"}
          </button>

        </form>

        <p className="auth-switch">
          Don't have an account?{" "}
          <button
            type="button"
            onClick={onShowSignup}
          >
            Sign Up
          </button>
        </p>

      </div>
    </div>
  );
}

export default Signin;