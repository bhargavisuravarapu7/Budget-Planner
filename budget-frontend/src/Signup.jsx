import { useState } from "react";
import api from "./services/api";

function Signup({ onSignupSuccess, onShowSignin }) {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
  });

  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!form.name.trim() || !form.email.trim() || !form.password) {
      alert("Please fill in all fields.");
      return;
    }

    try {
      setLoading(true);

      await api.post("/auth/signup", {
        name: form.name.trim(),
        email: form.email.trim(),
        password: form.password,
      });

      alert("Account created successfully!");

      if (onSignupSuccess) {
        onSignupSuccess();
      }
    } catch (error) {
      console.error("Signup error:", error);

      alert(
        error.response?.data?.message ||
        "Signup failed. Please try again."
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
          <h2>Create Account</h2>
          <p>Start managing your money today.</p>
        </div>

        <form onSubmit={handleSubmit}>

          <label>Full Name</label>
          <input
            type="text"
            placeholder="Enter your name"
            value={form.name}
            onChange={(e) =>
              setForm({
                ...form,
                name: e.target.value,
              })
            }
          />

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
            {loading ? "Creating Account..." : "Sign Up"}
          </button>

        </form>

        <p className="auth-switch">
          Already have an account?{" "}
          <button
            type="button"
            onClick={onShowSignin}
          >
            Sign In
          </button>
        </p>

      </div>
    </div>
  );
}

export default Signup;