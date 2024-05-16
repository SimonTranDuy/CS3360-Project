document.addEventListener("DOMContentLoaded", () => {
  renderProducts();
  updateCartCount();
});

let cartCount = 0; // Initialize cart count

function toggleCustomerInfo() {
  const infoContainer = document.getElementById("customer-info-container");
  const infoButton = document.getElementById("customer-info-button");
  if (infoContainer.style.display === "none") {
    infoContainer.style.display = "block";
    infoButton.textContent = "Hide Information";
  } else {
    infoContainer.style.display = "none";
    infoButton.textContent = "Customer Information";
  }
}
// function isInputInfo() {
//   const customerInfo = localStorage.getItem("customerInfo");
//   const infoContainer = document.getElementById(
//     "customer-info-container-fixed"
//   );
//   if (customerInfo) {
//     infoContainer.style.display = "block";
//   }
// }

function renderProducts() {
  const products = getProducts();
  const clothesSection = document.getElementById("clothes-section");
  const accessoriesSection = document.getElementById("accessories-section");
  console.log(products);
  //   products.forEach((product) => {
  //     const productCard = document.createElement("div");
  //     productCard.classList.add("product-card");

  //     productCard.innerHTML = `
  //             <img src="${product.image}" alt="${product.name}">
  //             <div class="product-info">
  //                 <h2>${product.name}</h2>
  //                 <div class="price">
  //                     <span class="current-price">${product.currentPrice}</span>
  //                 </div>
  //                 <p>${product.description}</p>
  //                 <div class="product-meta">
  //                     <span>Size: ${product.size}</span>
  //                     <span>Material: ${product.material}</span>
  //                     <span>Color: ${product.color}</span>
  //                 </div>
  //                 <button class="add-to-cart-button" onclick="addToCart()">Add to Cart</button>
  //             </div>
  //         `;

  //     if (product.type === "clothes") {
  //       clothesSection.appendChild(productCard);
  //     } else {
  //       accessoriesSection.appendChild(productCard);
  //     }
  //   });
}

function addToCart() {
  cartCount++;
  updateCartCount();
}

// function getProducts() {
//   return [
//     {
//       name: "Nike Running Shoes",
//       currentPrice: "$70.00",
//       description: "High-quality running shoes with great comfort and support.",
//       size: "M, L, XL",
//       material: "Mesh",
//       color: "Black",
//       image: "https://via.placeholder.com/150",
//       type: "clothes",
//     },
//     {
//       name: "Adidas Hoodie",
//       currentPrice: "$50.00",
//       description: "Comfortable and stylish hoodie for everyday wear.",
//       size: "S, M, L",
//       material: "Cotton",
//       color: "Gray",
//       image: "https://via.placeholder.com/150",
//       type: "clothes",
//     },
//     {
//       name: "Puma Sports Cap",
//       currentPrice: "$20.00",
//       description: "Lightweight cap perfect for sports and outdoor activities.",
//       size: "One Size",
//       material: "Polyester",
//       color: "Blue",
//       image: "https://via.placeholder.com/150",
//       type: "accessories",
//     },
//     {
//       name: "Under Armour T-shirt",
//       currentPrice: "$30.00",
//       description: "Breathable T-shirt for ultimate performance and comfort.",
//       size: "S, M, L, XL",
//       material: "Polyester",
//       color: "White",
//       image: "https://via.placeholder.com/150",
//       type: "clothes",
//     },
//     {
//       name: "Reebok Sports Bag",
//       currentPrice: "$40.00",
//       description: "Spacious and durable sports bag for all your gear.",
//       size: "One Size",
//       material: "Nylon",
//       color: "Red",
//       image: "https://via.placeholder.com/150",
//       type: "accessories",
//     },
//     {
//       name: "Nike Training Pants",
//       currentPrice: "$45.00",
//       description:
//         "Comfortable and flexible training pants for all activities.",
//       size: "M, L, XL, XXL",
//       material: "Cotton Blend",
//       color: "Black",
//       image: "https://via.placeholder.com/150",
//       type: "clothes",
//     },
//     {
//       name: "Adidas Sneakers",
//       currentPrice: "$80.00",
//       description: "Stylish and comfortable sneakers for everyday use.",
//       size: "M, L, XL",
//       material: "Leather",
//       color: "White",
//       image: "https://via.placeholder.com/150",
//       type: "clothes",
//     },
//     {
//       name: "Puma Jacket",
//       currentPrice: "$90.00",
//       description: "Warm and stylish jacket for cold weather.",
//       size: "S, M, L, XL",
//       material: "Polyester",
//       color: "Green",
//       image: "https://via.placeholder.com/150",
//       type: "clothes",
//     },
//     {
//       name: "Under Armour Shorts",
//       currentPrice: "$25.00",
//       description: "Lightweight and comfortable shorts for training.",
//       size: "S, M, L, XL",
//       material: "Polyester",
//       color: "Blue",
//       image: "https://via.placeholder.com/150",
//       type: "clothes",
//     },
//     {
//       name: "Reebok Running Shoes",
//       currentPrice: "$75.00",
//       description: "Durable and supportive running shoes for all terrains.",
//       size: "M, L, XL",
//       material: "Mesh",
//       color: "Gray",
//       image: "https://via.placeholder.com/150",
//       type: "clothes",
//     },
//     {
//       name: "Nike Cap",
//       currentPrice: "$18.00",
//       description: "Comfortable cap with adjustable strap.",
//       size: "One Size",
//       material: "Cotton",
//       color: "Black",
//       image: "https://via.placeholder.com/150",
//       type: "accessories",
//     },
//     {
//       name: "Adidas Backpack",
//       currentPrice: "$40.00",
//       description: "Spacious and durable backpack for daily use.",
//       size: "One Size",
//       material: "Nylon",
//       color: "Black",
//       image: "https://via.placeholder.com/150",
//       type: "accessories",
//     },
//     {
//       name: "Puma Socks",
//       currentPrice: "$9.00",
//       description: "Comfortable and breathable socks for sports.",
//       size: "One Size",
//       material: "Cotton Blend",
//       color: "White",
//       image: "https://via.placeholder.com/150",
//       type: "accessories",
//     },
//     {
//       name: "Under Armour Gloves",
//       currentPrice: "$15.00",
//       description: "Warm gloves perfect for cold weather.",
//       size: "S, M, L",
//       material: "Wool Blend",
//       color: "Black",
//       image: "https://via.placeholder.com/150",
//       type: "accessories",
//     },
//     {
//       name: "Reebok Headband",
//       currentPrice: "$8.00",
//       description: "Lightweight and comfortable headband for sports.",
//       size: "One Size",
//       material: "Cotton",
//       color: "White",
//       image: "https://via.placeholder.com/150",
//       type: "accessories",
//     },
//     {
//       name: "Nike Sports Bra",
//       currentPrice: "$25.00",
//       description: "Supportive and comfortable sports bra.",
//       size: "S, M, L",
//       material: "Polyester",
//       color: "Black",
//       image: "https://via.placeholder.com/150",
//       type: "clothes",
//     },
//   ];
// }

// function searchProducts() {
//   const searchInput = document
//     .getElementById("product-search-input")
//     .value.toLowerCase();
//   const products = getProducts();
//   const clothesSection = document.getElementById("clothes-section");
//   const accessoriesSection = document.getElementById("accessories-section");

//   clothesSection.innerHTML = "";
//   accessoriesSection.innerHTML = "";

//   const filteredProducts = products.filter((product) =>
//     product.name.toLowerCase().includes(searchInput)
//   );

//   filteredProducts.forEach((product) => {
//     const productCard = document.createElement("div");
//     productCard.classList.add("product-card");

//     productCard.innerHTML = `
//             <img src="${product.image}" alt="${product.name}">
//             <div class="product-info">
//                 <h2>${product.name}</h2>
//                 <div class="price">
//                     <span class="current-price">${product.currentPrice}</span>
//                 </div>
//                 <p>${product.description}</p>
//                 <div class="product-meta">
//                     <span>Size: ${product.size}</span>
//                     <span>Material: ${product.material}</span>
//                     <span>Color: ${product.color}</span>
//                 </div>
//                 <button class="add-to-cart-button" onclick="addToCart()">Add to Cart</button>
//             </div>
//         `;

//     if (product.type === "clothes") {
//       clothesSection.appendChild(productCard);
//     } else {
//       accessoriesSection.appendChild(productCard);
//     }
//   });
// }

function postCustomerInfo() {
  const phoneNumber = document.getElementById(
    "customer-phoneNumber-input"
  ).value;
  const name = document.getElementById("customer-name-input").value;
  const address = document.getElementById("customer-address-input").value;

  // Post the customer info to the server or process it as needed
  console.log("Customer Info:", { phoneNumber, name, address });
}

function displayAscendingOrder() {
  const products = getProducts();
  const sortedProducts = products.sort(
    (a, b) =>
      parseFloat(a.currentPrice.slice(1)) - parseFloat(b.currentPrice.slice(1))
  );
  displayProducts(sortedProducts);
}

function displayDescendingOrder() {
  const products = getProducts();
  const sortedProducts = products.sort(
    (a, b) =>
      parseFloat(b.currentPrice.slice(1)) - parseFloat(a.currentPrice.slice(1))
  );
  displayProducts(sortedProducts);
}

// function displayProducts(products) {
//   const clothesSection = document.getElementById("clothes-section");
//   const accessoriesSection = document.getElementById("accessories-section");

//   clothesSection.innerHTML = "";
//   accessoriesSection.innerHTML = "";

//   products.forEach((product) => {
//     const productCard = document.createElement("div");
//     productCard.classList.add("product-card");

//     productCard.innerHTML = `
//             <img src="${product.image}" alt="${product.name}">
//             <div class="product-info">
//                 <h2>${product.name}</h2>
//                 <div class="price">
//                     <span class="current-price">${product.currentPrice}</span>
//                 </div>
//                 <p>${product.description}</p>
//                 <div class="product-meta">
//                     <span>Size: ${product.size}</span>
//                     <span>Material: ${product.material}</span>
//                     <span>Color: ${product.color}</span>
//                 </div>
//                 <button class="add-to-cart-button" onclick="addToCart()">Add to Cart</button>
//             </div>
//         `;

//     if (product.type === "clothes") {
//       clothesSection.appendChild(productCard);
//     } else {
//       accessoriesSection.appendChild(productCard);
//     }
//   });
// }

function updateCartCount() {
  document.getElementById("number-of-items-in-cart").textContent = cartCount;
}
