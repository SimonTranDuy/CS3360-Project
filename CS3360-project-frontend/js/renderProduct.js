const baseURL = "http://localhost:8080/api/v1/";
let minPrice, maxPrice;
// function render all products and slider by GET method when init page
document.addEventListener("DOMContentLoaded", function () {
  getData(baseURL + "products/get-all").then((data) => {
    // display all fetched data
    displaySearchResults(data.data);
    // console.log(dataToRender);
  });
  if (window.localStorage.getItem("cart")) {
    updateNumberOfItemsInCart();
  }
});
// fetch data from API getAllProducts
async function getData(url = baseURL) {
  try {
    const response = await fetch(
      "http://localhost:8080/api/v1/products/get-all",
      {
        method: "GET",
        mode: "cors", // Use "cors" instead of "no-cors"
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    let data = await response.json();
    console.log(data);
    return data;
    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}

async function postCustomerInfo() {
  const customerInfo = {
    customerName: document.getElementById("customer-name-input").value,
    phoneNumber: document.getElementById("customer-phoneNumber-input").value,
    address: document.getElementById("customer-address-input").value,
  };
  console.log(customerInfo);
  try {
    const response = await fetch(
      "http://localhost:8080/api/v1/customers/insert",
      {
        method: "POST",
        mode: "cors", // Use "cors" instead of "no-cors"
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(customerInfo),
      }
    );
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    let data = await response.json();
    console.log(data);
    localStorage.setItem("customerInfo", JSON.stringify(data.data));
    return data;
    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}

async function displayCurrentCart() {
  const customerId = JSON.parse(
    localStorage.getItem("customerInfo")
  ).customerId;
  try {
    const response = await fetch(
      "http://localhost:8080/api/orderItems/get-all/" + customerId,
      {
        method: "POST",
        mode: "cors", // Use "cors" instead of "no-cors"
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    let data = await response.json();
    console.log(data);
    localStorage.setItem("cart", JSON.stringify(data.data));
    return data;
    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}
// function get products by NAME with GET method
async function searchProducts() {
  // Get the input value
  const productName = document.getElementById("searchInput").value;
  if (productName.length > 0) {
    try {
      const response = await fetch(
        baseURL + "products/get-by-name/" + encodeURIComponent(productName),
        {
          method: "GET",
          mode: "cors", // Use "cors" instead of "no-cors"
          cache: "no-cache",
          credentials: "same-origin",
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
        .then((res) => res.json())
        .then((data) => {
          // display response data
          displaySearchResults(data.data);
        });

      // return await response.json();
      //   console.log(data); // Log or process the data as needed
    } catch (error) {
      console.error("Error fetching search results:", error);
    }
  } else {
    try {
      const response = await fetch(baseURL + "products/get-all", {
        method: "GET",
        mode: "cors", // Use "cors" instead of "no-cors"
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((res) => res.json())
        .then((data) => {
          displaySearchResults(data.data);
        });

      // return await response.json();
      //   console.log(data); // Log or process the data as needed
    } catch (error) {
      console.error("Error fetching search results:", error);
    }
  }
}

// GET products by price ascending order
async function displayAscendingOrder() {
  try {
    const response = await fetch(baseURL + "products/get-all-asc", {
      method: "GET",
      mode: "cors", // Use "cors" instead of "no-cors"
      cache: "no-cache",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((res) => res.json())
      .then((data) => {
        // display response data
        displaySearchResults(data.data);
        // console.log(data);
      });

    // return await response.json();
    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching search results:", error);
  }
}
// GET products by price ascending order
async function displayDescendingOrder() {
  try {
    const response = await fetch(baseURL + "products/get-all-desc", {
      method: "GET",
      mode: "cors", // Use "cors" instead of "no-cors"
      cache: "no-cache",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((res) => res.json())
      .then((data) => {
        // display response data
        displaySearchResults(data.data);
        // console.log(data);
      });

    // return await response.json();
    //   console.log(data); // Log or process the data as needed
  } catch (error) {
    console.error("Error fetching search results:", error);
  }
}

// function display all received data, every function above call this function to render the results
function displaySearchResults(results) {
  console.log(results);
  const resultsContainer = document.getElementById("table-body");
  resultsContainer.innerHTML = ""; // Clear previous results

  if (!results) {
    resultsContainer.innerHTML = "<p>No results found</p>";
  } else {
    // Assuming each result is an object with a 'productName' property
    results.map((results) => {
      const row = document.createElement("tr");
      row.innerHTML = `
          <td>${results.productName}</td>
          <td>${results.price}</td>
          <td>${results.brand}</td>
          <td>${results.size}</td>
          <td>${results.material}</td>
          <td>${results.weight}</td>
          <td><input type="number" value="1"></input></td>
          <td>${results.description}</td>
          <td><button class="add-to-cart-btn">Add to cart</button></td>
      `;

      // Find the button inside the row and add an event listener to it
      const addToCartButton = row.querySelector(".add-to-cart-btn");
      addToCartButton.addEventListener("click", () => {
        results.quantity = parseInt(
          row.querySelector("input[type=number]").value
        );
        // Add the product to cart
        addToCart(results);
        updateNumberOfItemsInCart();
      });

      resultsContainer.appendChild(row);
      // console.log(results);
    });
  }
}

// Add a product to the cart using both post method postNewOrdersItem and save that object data to localStorage.
function addToCart(product) {
  saveOrdersItemToLocalStorage(product);
  postNewOrdersItem(product);
}
// rerender the number of items in the cart
function updateNumberOfItemsInCart() {
  const cartData = JSON.parse(localStorage.getItem("cart"));
  let total = 0;
  cartData.map((item) => {
    // console.log(item.quantity);
    total += item.quantity;
  });
  const numberOfItemsInCart = document.getElementById(
    "number-of-items-in-cart"
  );
  numberOfItemsInCart.innerHTML = total;
}

// save OrdersItem to localStorage
function saveOrdersItemToLocalStorage(product) {
  // Retrieve the cart from local storage and parse it into an array
  let cart = JSON.parse(localStorage.getItem("cart")) || [];

  // Check if the product already exists in the cart
  const existingProductIndex = cart.findIndex((p) => p.id === product.id);
  if (existingProductIndex > -1) {
    // Update quantity if product exists
    cart[existingProductIndex].quantity += product.quantity;
  } else {
    // Add new product to the cart
    cart.push(product);
  }

  // Save the updated cart back to local storage
  localStorage.setItem("cart", JSON.stringify(cart));
}

function postNewOrdersItem(product) {
  console.log(product);
  const newDataToSend = {
    product: {
      id: product.id,
    },
    quantity: product.quantity,
    coupon: {},
  };
}
