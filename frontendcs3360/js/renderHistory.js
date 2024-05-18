// Hàm để lấy và hiển thị dữ liệu lịch sử đơn hàng từ backend
async function fetchOrderHistory(order = "desc") {
  const customerInfo = JSON.parse(localStorage.getItem("customerInfo"));
  const customerId = customerInfo.customerId;
  console.log(customerId);
  const url =
    order === "asc"
      ? `http://localhost:8080/api/orderItems/historyAsc/${customerId}`
      : `http://localhost:8080/api/orderItems/historyDesc/${customerId}`;
  console.log(url);
  const testURL = `http://localhost:8080/api/orderItems/historyAsc/1`;
  console.log(customerInfo);
  try {
    const response = await fetch(testURL, {
      method: "GET",
      mode: "cors", // Use "cors" instead of "no-cors"
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    let data = await response.json();
    console.log(data);
    localStorage.setItem("orderHistoryData", JSON.stringify(data.data));
    return data;
    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}

// Thêm sự kiện click cho các nút sắp xếp và làm mới
const renderOrderHistory = () => {
  const tbody = document.getElementById("order-history-body");
  tbody.innerHTML = ""; // Xóa nội dung cũ
  const data = JSON.parse(localStorage.getItem("orderHistoryData"));

  data.orderItems.forEach((orderItem) => {
    const row = document.createElement("tr");
    row.innerHTML = `
    <td class="px-4 py-2">${orderItem.item.productName}</td>
    <td class="px-4 py-2">${orderItem.quantity}</td>
    <td class="px-4 py-2">${orderItem.item.price}</td>
    <td class="px-4 py-2">${orderItem.item.price * orderItem.quantity}</td>
    <td class="px-4 py-2">${new Date(orderItem.dateOfPurchase).toLocaleString()}</td>

            `;
    tbody.appendChild(row);
  });
};

// Gọi hàm fetchOrderHistory với 'desc' khi trang được tải
document.addEventListener("DOMContentLoaded", async function () {
  await fetchOrderHistory();
  renderOrderHistory();
});
