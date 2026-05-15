## Create and show URLs

### Functional Requirement

* We need to create form to create short url and
  need to create My Short URLs menu which will show
  logged-in user's short URLs as per the PRD

### DB changes

* Create roles table with below columns:
  * id : bigint (PK, AI)
  * name : varchar(20) (NN)
  * description : varchar(255) (NN)
* Insert below roles:
  * System : Responsible to automate things
  * Admin : Responsible for everything with all permissions
  * Customer : Responsible to manage URLs with only required permissions
* Create users table with below columns:
  * id : bigint (PK, AI)
  * name : varchar(45) (NN)
  * email : varchar(45) (NN, UQ)
  * role_id : bigint (FK -> role, NN)
  * delete_flag : bit(1) (default -> 0, NN)
  * created_by : bigint (FK -> users)
  * created_date : datetime (default -> UTC(), NN)
  * last_updated_by : bigint (FK -> users)
  * last_updated_date : datetime
* Create url_status table with below columns:
  * id : bigint (PK, AI)
  * name : varchar(50) (NN)
  * description : varchar(255) (NN)
  * delete_flag : bit(1) (default -> 0, NN)
  * created_by : bigint (FK -> users, NN)
  * created_date : datetime (default -> UTC(), NN)
  * last_updated_by : bigint (FK -> users)
  * last_updated_date : datetime
* Insert below url statues
  * Active : "Represents active urls"
  * Inactive : "Represents inactive urls"
* Create urls table with below columns:
  * id : bigint (PK, AI)
  * title : varchar(128) (NN)
  * original_url : varchar(2048) (NN)
  * slug : varchar(50) (NN)
  * url_status_id : bigint (FK -> url_status, NN)
  * delete_flag : bit(1) (default -> 0, NN)
  * created_by : bigint (FK -> users, NN)
  * created_date : datetime (default -> UTC(), NN)
  * last_updated_by : bigint (FK -> users)
  * last_updated_date : datetime

### Backend changes

* create new POST endpoint /api/ums/urls/create-url
* Note : ? in request JSON property indicates that property is optional
* Request Headers

```shell
userid : 1
roleid : 3
device : web
```
* Request Payload

```json
{
  "title" : "Database Exploration Checklist for Understanding a New Domain",
  "originalUrl" : "https://abhishekmalvadkar.netlify.app/database-exploration-checklist-for-understanding-a-new-domain/",
  "slug?" : "DbExploration"
}
```
* Response Payload

```json
{
  "data": {
    "id": 1
  },
  "message": "Created successfully",
  "code": 201,
  "status": "CREATED"
}
```

* create new POST endpoint /api/ums/urls/fetch-urls
* Request Headers

```shell
userid : 1
roleid : 3
device : web
```
* Request Payload

```json
{
  "urlStatusId?" : 1
}
```
* Response Payload

```json
{
  "data": {
    "headers" : [
      {
        "displayName": "Short Url",
        "mappingName": "shortUrl",
        "headerType": "text",
        "headerMappingId": 1,
        "editable" : false,
        "filterable": true,
        "sortable": true,
        "optionSource" : null
      },
      {
        "displayName": "Title",
        "mappingName": "title",
        "headerType": "text",
        "headerMappingId": 2,
        "editable" : true,
        "filterable": true,
        "sortable": true,
        "optionSource" : null
      },
      {
        "displayName": "Original Url",
        "mappingName": "originalUrl",
        "headerType": "text",
        "headerMappingId": 3,
        "editable" : true,
        "filterable": true,
        "sortable": true,
        "optionSource" : null
      },
      {
        "displayName": "Slug",
        "mappingName": "slug",
        "headerType": "text",
        "headerMappingId": 4,
        "editable" : true,
        "filterable": true,
        "sortable": true,
        "optionSource" : null
      },
      {
        "displayName": "Status",
        "mappingName": "urlStatusId",
        "headerType": "dropdown",
        "headerMappingId": 5,
        "editable" : true,
        "filterable": true,
        "sortable": true,
        "optionSource" : "urlStatusList"
      },
      {
        "displayName": "Created On",
        "mappingName": "createdDate",
        "headerType": "date",
        "headerMappingId": 6,
        "editable" : false,
        "filterable": false,
        "sortable": true,
        "optionSource" : null
      },
      {
        "displayName": "",
        "mappingName": "",
        "headerType": "copy",
        "headerMappingId": 7,
        "editable" : false,
        "filterable": false,
        "sortable": false,
        "optionSource" : null
      }
    ],
    "data" : [
      {
        "id": 1,
        "shortUrl" : "https://ourdomain.com/slug",
        "title" : "My Short URL Title",
        "originalUrl": "https://original-url.com",
        "slug" : "DbExploration",
        "urlStatusId" : 1,
        "createdDate" : "2026-01-05T11:33:00Z"
      }
    ]
  },
  "message": "Fetched successfully",
  "code": 200,
  "status": "SUCCESS"
}
```

* create new PATCH endpoint /api/ums/urls/update-url
* Request Headers

```shell
userid : 1
roleid : 3
device : web
```
* Request Payload

```json
{
  "headerMappingId" : 5,
  "value": "2",
  "recordId": 1
}
```
* Response Payload

```json
{
  "data": {
    "id": 1
  },
  "message": "Updated successfully",
  "code": 200,
  "status": "SUCCESS"
}
```
