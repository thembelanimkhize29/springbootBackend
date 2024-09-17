# springbootBackend
get list of clients
http://localhost:8080/clients/list

get list of contacts
http://localhost:8080/contacts/all

http://localhost:8080/clients/count-linked-contacts?clientName=Capitec

http://localhost:8080/contacts/count-linked-clients?email=thembani@example.com

create client with/without contacts

http://localhost:8080/clients/create

{
"firstName": "zpitec Bank South",
"contacts": [
{
"name": "Thembani",
"surname": "Mkhize",
"email": "thembaniss@example.com"
},
{
"name": "Thembani",
"surname": "Mkhize",
"email": "thembanizzz@example.com"
}
]
}

create contact with/without clients

http://localhost:8080/contacts/create-with-clients

{
"name": "swasambani",
"surname": "Mkhize",
"email": "sanelekeys@gmail.com",
"clients": [
{
"firstName": "First Satonal Mank"
},
{
"firstName": "Zapitec"
}
]
}

without

{
"name": "swasambani",
"surname": "Mkhize",
"email": "sanelekeys@gmail.com"
}