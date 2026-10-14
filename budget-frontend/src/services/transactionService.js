import api from "./api";

// Get transactions for a specific user
export const getTransactions = async (userId) => {
  const response = await api.get("/transactions", {
    params: { userId },
  });

  return response.data;
};

// Add a new transaction for a specific user
export const addTransaction = async (transaction, userId) => {
  const response = await api.post(
    "/transactions",
    transaction,
    {
      params: { userId },
    }
  );

  return response.data;
};

// Delete a transaction belonging to a specific user
export const deleteTransaction = async (id, userId) => {
  await api.delete(`/transactions/${id}`, {
    params: { userId },
  });
};

// Update a transaction belonging to a specific user
export const updateTransaction = async (
  id,
  transactionData,
  userId
) => {
  const response = await api.put(
    `/transactions/${id}`,
    transactionData,
    {
      params: { userId },
    }
  );

  return response.data;
};