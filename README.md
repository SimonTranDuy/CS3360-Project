
<h1>DATABASE DESIGN</h1>
<image src="db.png"></image>


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
  // order_code integer [primary key, ref: > order.code] 
  code integer [primary key]
  customer_id integer [primary key, ref: > customer.id]
  item_id integer [primary key, ref: > item.id]
  quantity integer
  
}

Table purchased_order {
  code integer [primary key]
  date_of_purchase timestamp
}
```

<br/>
<br/>
<br/>
<br/>
<br/>

<h1>CLASS DESIGN</h1>
<image src="classDesign.png"></image>


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
</ul>
