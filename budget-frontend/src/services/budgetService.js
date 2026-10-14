import api from "./api";

// Get budget for a specific user
export const getBudget = async (userId) => {
  const response = await api.get("/budget", {
    params: { userId },
  });

  return response.data;
};

// Save budget for a specific user
export const saveBudget = async (monthlyLimit, userId) => {
  const response = await api.post(
    "/budget",
    {
      monthlyLimit: Number(monthlyLimit),
    },
    {
      params: { userId },
    }
  );

  return response.data;
};