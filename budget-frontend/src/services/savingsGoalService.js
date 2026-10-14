import api from "./api";

// Get savings goals for a specific user
export const getSavingsGoals = async (userId) => {
  const response = await api.get("/savings-goals", {
    params: { userId },
  });

  return response.data;
};

// Add a savings goal for a specific user
export const addSavingsGoal = async (goal, userId) => {
  const response = await api.post(
    "/savings-goals",
    goal,
    {
      params: { userId },
    }
  );

  return response.data;
};

// Update a savings goal belonging to a specific user
export const updateSavingsGoal = async (id, goal, userId) => {
  const response = await api.put(
    `/savings-goals/${id}`,
    goal,
    {
      params: { userId },
    }
  );

  return response.data;
};

// Delete a savings goal belonging to a specific user
export const deleteSavingsGoal = async (id, userId) => {
  await api.delete(`/savings-goals/${id}`, {
    params: { userId },
  });
};