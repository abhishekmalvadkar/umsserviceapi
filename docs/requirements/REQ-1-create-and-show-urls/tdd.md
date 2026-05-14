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
* Create urls table with below columns:
  * id : bigint (PK, AI)
  * title : varchar(128) (NN)
  * original_url : varchar(2048) (NN)
  * slug : varchar(50) (NN)
  * status : varchar(50) (default -> Active, NN)
  * delete_flag : bit(1) (default -> 0, NN)
  * created_by : bigint (FK -> users, NN)
  * created_date : datetime (default -> UTC(), NN)
  * last_updated_by : bigint (FK -> users)
  * last_updated_date : datetime

### Backend changes

* create new POST endpoint /api/ums/urls/create-url
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
  "slug" : "DbExploration"
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
