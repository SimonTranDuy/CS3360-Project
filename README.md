
<h1>DATABASE DESIGN</h1>
<image src="Design/db.png"></image>

<h2>- NẾU TRẢ VỀ BẢN GHI THÌ LẤY LIST OF ITEMS ID ĐÓ ĐỂ ADD ITEM MỚI VÀO DỰA TRÊN LIST OF ITEMS ID ĐÓ (THÊM VÀO GIỎ HÀNG)</h2>
<h2>- NẾU NHƯ KO TRẢ VỀ GÌ (GIỎ HÀNG TRỐNG) THÌ GENERATE LIST OF ITEMS ID MỚI VÀ INSERT VÀO BẢNG</h2>
<h2>- DỰA VÀO CUSTOMER ID VÀ DATE OF PURCHASE ĐỂ LẤY CÁC ĐƠN HÀNG ĐÃ MUA, CÁC ĐƠN HÀNG CÓ CÙNG DATE OF PURCHASE GỘP VÀO MỘT ĐƠN, CÁC ĐƠN HÀNG DATE OF PURCHASE NULL LÀ HÀNG CHƯA THANH TOÁN TRONG GIỎ</h2>

```sql
SELECT customer_id = ? 
FROM list_of_items
WHERE date_of_purchase = NULL;
```
<br/>
<br/>

```sql
enum Material {
  "golden" 
  "sliver"
  "copper"
}

Table customer {
  id integer [primary key]
  username varchar(255)
  phone_number varchar(10)
  address text
}

Table item {
  id integer [primary key]
  name varchar(255)
  price double
  description text
}

Table clothes {
  id integer [primary key, ref: < item.id]
  brand varchar(255)
  size varchar(255)
}

Table accessories {
  id integer [primary key, ref: < item.id]
  material Material
  type varchar(255)
  weight double
}


Table list_of_items {
  list_of_items_id integer
  customer_id integer [primary key, ref: > customer.id]
  item_id integer [primary key, ref: > item.id]
  quantity integer
  date_of_purchase datetime
  
}
```

<br/>
<br/>
<br/>
<br/>
<br/>

<h1>CLASS DESIGN</h1>
<image src="Design/classDesign.png"></image>


<br/>
<br/>
<br/>
<br/>
<br/>

<h1>SCHEDULE</h1>
<ul>
    <li>07/05/2024: Dựng Model (Class) - Database: thống nhất các mối quan hệ khi map từ DB sang class object, add các methods: getter, setter, constructor,...</li>
    <li>12/05/2024: Dựng Controller - Service: dựng các luồng đi chính, test các API để chuẩn bị làm frontend</li>
    <li>13/05/2024: Hỏi góp ý của thầy debug</li>
    <li>18/05/2024: Test toàn bộ chương trình</li>
    <li>20/05/2024: Thuyết trình</li>
</ul>

<h1>UPDATE NOTES</h1>
<ul>
    <li>02/05/2024: Init project</li>
    <li>05/05/2024: Đã xong models, đang làm repositories</li>
    <li>09/05/2024: Đã dựng xong cơ bản của luồng liên quan đến Item, thiếu các luồng findby...</li>
</ul>
