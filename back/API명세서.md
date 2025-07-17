<h1 style='background-color: rgba(55, 55, 55, 0.4); text-align: center'>API 명세서 </h1>

해당 API 명세서는 'Food Insight'의 REST API를 명세하고 있습니다.

- Domain : <http://localhost:9999>  

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Auth 모듈</h2>

인증 및 인가와 관련된 REST API 모듈  
로그인, 회원가입, 소셜 로그인, 소셜 회원가입 등의 API가 포함되어 있습니다.  

- url : /api/v1/auth  

***

#### - 로그인  

##### 설명

클라이언트로부터 사용자 아이디와 평문의 비밀번호를 입력받고 아이디와 비밀번호가 일치한다면 성공 처리가 되며 access_token과 해당 토큰의 만료 기간을 반환합니다. 만약 아이디 혹은 비밀번호가 하나라도 틀리다면 실패 처리를 합니다. 서버 에러, 데이터베이스 에러, 토큰 생성 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/sign-in**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userEmailId | String | 사용자 아이디(이메일 형식) | O |
| userPassword | String | 사용자의 비밀번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/auth/sign-in" \
 -d "userEmailId=service123@email.com" \
 -d "userPassword=P!ssw0rd"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| accessToken | String | 사용자의 토큰 | O |
| expires | int | 토큰 만료 기간 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "accessToken": "${ACCESS_TOKEN}",
  "expires": 3600
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (로그인 정보 불일치)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8
{
  "code": "SF",
  "message": "Sign in Failed."
}
```

**응답 : 실패 (토큰 생성 실패)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "TF",
  "message": "Token creation Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 이메일 중복 확인  
  
##### 설명

클라이언트로부터 이메일을 입력받아 해당하는 이메일이 이미 사용중인 이메일인지 확인합니다. 중복되지 않은 이메일이면 성공 처리를 합니다. 만약 중복되는 아이디라면 실패 처리를 합니다. 데이터 유효성 검사 실패, 중복된 이메일, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/email-check**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userEmailId | String | 중복 확인할 사용자의 아이디(이메일 형식) | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/auth/email-check" \
 -d "userEamilId=service123@email.com" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (중복된 이메일)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "DE",
  "message": "Duplicatied Email."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 닉네임 중복 확인  
  
##### 설명

클라이언트로부터 닉네임을 입력받아 해당하는 닉네임이 이미 사용중인 닉네임인지 확인하고 사용하고 있지 않은 닉네임이라면 성공 처리를 합니다. 만약 중복된 닉네임이라면 실패 처리를 합니다. 데이터 유효성 검사 실패, 중복된 닉네임, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/nickname-check**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| nickname | String | 중복 확인할 사용자의 닉네임 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/auth/nickname-check" \
 -d "nickname=service22"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (중복된 닉네임)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "DN",
  "message": "Duplicatied Nickname."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 사업자등록번호 중복 확인  
  
##### 설명

클라이언트로부터 사업자등록번호를 입력받아 해당하는 사업자등록번호가 이미 사용중인 사업자등록번호인지 확인하고 사용하고 있지 않은 사업자등록번호라면 성공 처리를 합니다. 만약 중복된 사업자등록번호라면 실패 처리를 합니다. 데이터 유효성 검사 실패, 중복된 사업자등록번호, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **s/busines-registration-check**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| businessRegistrationNumber | String | 중복 확인할 사용자의 사업자등록번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/auth/busines-registration-check" \
 -d "businessRegistrationNumber=222-22-22222"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (중복된 사업자등록번호)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "DR",
  "message": "Duplicated Business Registration Number."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 전화번호 인증
  
##### 설명

클라이언트로부터 전화번호를 입력받아 6자리의 인증 결과 코드를 해당 전화번호로 전송합니다. 전화번호로 전송이 성공적으로 종료되었으면 성공 처리를 합니다. 만약 전화번호로 전송에 실패했으면 실패 처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/tel-number-auth**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userTelNumber | String | 인증 번호를 전송할 사용자 전화번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/auth/tel-number-auth" \
 -d "userTelNumber=010-0000-0000" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (전화번호 전송 실패)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "SF",
  "message": "Auth Number Send Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```
***

#### - 전화번호 인증 확인
  
##### 설명

클라이언트로부터 전화번호와 인증 번호를 입력받아 해당하는 전화번호에 전송한 인증번호와 일치하는지 확인합니다. 일치한다면 성공 처리를 합니다. 만약 일치하지 않는다면 실패처리를 합니다. 데이터베이스 오류가 발생할 수 있습니다.

- method : **POST**  
- URL : **/tel-number-check**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userTelNumber | String | 인증 번호를 확인할 사용자 전화번호 | O |
| authNumber | String | 인증 확인할 인증 번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/auth/email-auth-check" \
 -d "userTelNumber=010-0000-0000" \
 -d "authNumber=012345"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Varidation Failed."
}
```

**응답 : 실패 (전화번호 인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 회원가입
  
##### 설명

클라이언트로부터 이메일, 비밀번호, 닉네임, 이름, 전화번호, 인증번호, 주소, 사업자등록번호를 입력받아 회원가입 처리를 합니다. 정상적으로 회원가입이 완료되면 성공 처리를 합니다. 만약 중복된 이메일, 중복된 닉네임, 중복된 사업자등록번호, 인증번호 불일치가 발생하면 실패처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/sign-up**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userEmailId | String | 사용자 아이디(이메일 형식) | O |
| userPassword | String | 사용자 비밀번호(영문+숫자 8~13자) | O |
| nickname | String | 사용자 닉네임 | O |
| userName | String | 사용자 이름 | O |
| userTelNumber | String | 사용자 전화번호 | O |
| authNumber | String | 인증 확인할 인증 번호 | O |
| userAddress | String | 사용자 주소 | O |
| businessRegistrationNumber | String | 사용자 사업자등록번호 | X |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/auth/sign-up" \
 -d "userEmailId=service123@email.com" \
 -d "userPassword=P!ssw0rd" \
 -d "nickname=service22" \
 -d "userName=홍길동" \
 -d "userTelNumber=010-0000-0000" \
 -d "authNumber=012345" \
 -d "userAddress=부산광역시" \
 -d "businessRegistrationNumber=222-22-22222"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (중복된 이메일)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "DE",
  "message": "Duplicatied Email."
}
```

**응답 : 실패 (중복된 닉네임)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "DN",
  "message": "Duplicatied Nickname."
}
```

**응답 : 실패 (중복된 사업자등록번호)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "DR",
  "message": "Duplicated Business Registration Number."
}
```

**응답 : 실패 (전화번호 인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 아이디(이메일 찾기)
  
##### 설명

클라이언트로부터 이름, 전화번호를 입력받아 이메일을 확인할 수 있습니다. 입력된 이름과 전화번호가 일치하는 사용자의 이메일 일부분을 보여주어 성공 처리를 합니다. 실패하면 실패 처리를 합니다. 사용자 정보 불일치, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/find-email**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userName | String | 사용자 이름 | O |
| userTelNumber | String | 사용자 전화번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/auth/sign-up" \
 -d "userName=홍길동" \
 -d "userTelNumber=010-0000-0000" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| userEmailId | String | 사용자 아이디(이메일 형식)</br>(첫글자를 제외한 나머지 문자는 *) | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (사용자 정보 불일치)**
```bash
HTTP/1.1 404 Not Found
Content-Type: application/json;charset=UTF-8
{
  "code": "NF",
  "message": "Not Found User."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 비밀번호 재설정 요청
  
##### 설명

클라이언트로부터 아이디, 전화번호를 입력받아 입력된 아이디와 전화번호가 일치하는 사용자를 찾으면 성공 처리를 합니다. 실패하면 실패 처리를 합니다. 사용자 정보 불일치, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/password-reset**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userEmailId | String | 사용자 아이디(이메일 형식) | O |
| userTelNumber | String | 사용자 전화번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/auth/sign-up" \
 -d "userEmailId=service123@email.com" \
 -d "userTelNumber=010-0000-0000" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (사용자 정보 불일치)**
```bash
HTTP/1.1 404 Not Found
Content-Type: application/json;charset=UTF-8
{
  "code": "NF",
  "message": "Not Found User."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 새로운 비밀번호 설정
  
##### 설명

클라이언트로부터 새로운 비밀번호를 입력받아 비밀번호를 재설정하여 성공 처리를 합니다. 실패하면 실패 처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/password-update/${userEmailId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| password | String | 재설정할 비밀번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/auth/sign-up" \
 -d "password=P!ssw0rd00" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>User 모듈</h2>

사용자 정보와 관련된 REST API 모듈 
  
- url : /api/v1/user  

***

#### - 로그인 유저 정보 반환
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 요청을 받으면 해당 토큰의 작성자(subject)에 해당하는 사용자 정보를 반환합니다. 성공 시에는 사용자의 아이디, 권한을 반환합니다. 인증 실패 및 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/user/" \
 -H "Authorization: Bearer {JWT}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| userEmailId | String | 사용자의 아이디(이메일 형식) | O |
| userRole | String | 사용자의 권한 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "uesrId": "${userId}",
  "userRole": "${userRole}"
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 회원 정보 수정
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 닉네임, 주소를 입력받고 회원 정보를 수정합니다. 성공 시에는 성공 처리를 합니다. 인증 실패, 인가 실패, 유효성 검사 실패, 중복된 닉네임, 데이터베이스 에러가 발생할 수 있습니다.

- method : **PATCH**  
- URL : **/info-uppdate**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| nickname | String | 사용자 닉네임 | O |
| userAddress | String | 사용자 주소 | O |

###### Example

```bash
curl -v -X PATCH "http://localhost:9999/api/v1/user/info-update" \
 -d "nickname=service56" \
 -d "userAddress=서울특별시"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (중복된 닉네임)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "DN",
  "message": "Duplicatied Nickname."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 회원 탈퇴
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 비밀번호를 입력받고 회원 탈퇴를 합니다. 성공 시에는 성공 처리를 합니다. 인증 실패, 인가 실패, 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/info-delete**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userPassword | String | 사용자 비밀번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/user/info-delete" \
 -d "userPassword=P!ssw0rd" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Restaurant 모듈</h2>

식당 정보와 관련된 REST API 모듈  
  
- url : /api/v1/restaurant  

***

#### - 식당 검색 목록 불러오기
  
##### 설명

클라이언트로부터 식당명을 입력받고 요청을 보내면 데이터베이스에 저장된 순서로 식당 목록을 반환합니다. 만약 불러오기에 실패하면 실패 처리됩니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/search**  

##### Request

###### Query Param

| name | type | description | required |
|---|:---:|:---:|:---:|
| word | String | 검색할 식당명 | O |

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/restaurant/search?word=${searchword}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| restaurantList | restaurantListItem[] | 식당 리스트 | O |

**RestaurantListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| restaurantId | int | 식당 번호 | O |
| restaurantImage | String | 식당 대표 사진 | O |
| restaurantName | String | 식당명 | O |
| restaurantFoodCategory | String | 식당 음식 카테고리 | O |
| restaurantLocation | String | 식당 주소 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.", 
  "restaurantListItem": [
    {
      "restaurantId": ${restaurantId},
      "restaurantImage": ${restaurantImage},
      "restaurantName": ${restaurantName},
      "restaurantFoodCategory": ${restaurantFoodCategory},
      "restaurantLocation": ${restaurantLocation}
    }, ...
  ]
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 식당 상세 페이지 불러오기
  
##### 설명

클라이언트로부터 요청을 받고 불러오기에 성공하면 성공 처리를 합니다. 만약 불러오기에 실패하면 실패 처리됩니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/{restaurantId}**  

##### Request

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| restaurantId | int | 식당 번호 | O |

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/restaurant/${restaurantId}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| restaurantID | int | 식당 번호 | O |
| restaurantImage | String | 식당 대표 사진 | O |
| restaurantName | String | 식당명 | O |
| restaurantFoodCategory | String | 식당 음식 카테고리 | O |
| retaurantLocation | String | 식당 주소 | O |
| restaurantTelNumber | String | 식당 전화번호 |  |
| restaurantSnsAddress | String | 식당 SNS 주소 |  |
| restaurantOperationHours | String | 식당 운영 시간 |  |
| restaurantFeatures | String | 식당 특징 |  |
| restaurantNotice | String | 식당 공지 |  |
| restaurantRepresentativeMenu | String | 식당 대표 메뉴 |  |
| restaurntBusinessRegistrationNumber | String | 식당 사업자등록번호 | O |
| restaurantWriterId | String | 식당 등록자 아이디 | O |
| restaurantReviewList | restaurantReviewListItem[] | 식당 리뷰 리스트 | O |

**RestaurantReviewListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| reviewNumber | int | 리뷰 접수 번호 | O |
| reviewRestaurantId | int | 식당 번호 | O |
| reviewImage | String | 리뷰 사진 |  |
| rating | double | 평점 | O |
| reviewContents | String | 리뷰 내용 |  |
| reviewWriterId | String | 작성자 아이디 | O |
| reviewWriterNickname | String | 작성자 닉네임 | O |
| reviewDate | datetime | 작성일 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.", 
  "restaurantId": ${restaurantId}
  "restaurantImage": ${restaurantImage},
  "restaurantName": ${restaurantName},
  "restaurantFoodCategory": ${restaurantFoodCategory},
  "restaurantPostalCode": ${restaurantPostalCode},
  "restaurantLocation": ${restaurantLocation},
  "restaurantTelNumber": ${restaurantTelNumber},
  "restaurantSnsAddress": ${restaurantSnsAddress},
  "restaurantOperationHours": ${restaurantOperationHours},
  "restaurantFeatures": ${restaurantFeatures},
  "restaurantNotice": ${restaurantNotice},
  "restaurantRepresentativeMenu": ${restaurantRepresentativeMenu},
  "restaurantBuisnessRegistrationNumber": ${restaurantBuisnessRegistrationNumber},
  "restaurantReviewLisyItem": [
    {
      "reviewNumber": ${reviewNumber},
      "reviewRestaurantId": ${reviewRestaurantId},
      "reviewImage": ${reviewImage},
      "rating": ${rating},
      "reviewContents": ${reviewContents},
      "reviewWriterId": ${reviewWriterId},
      "reviewWriterNickname": ${reviewWriterNickname},
      "reviewDate": ${reviewDate}
    }, ...
  ]
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 식당 정보 등록
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 식당 상세 정보(대표사진, 식당명, 음식카테고리, 우편번호, 주소, 연락처, sns주소, 운영시간, 특징, 공지, 대표메뉴)를 입력받고 등록에 성공하면 성공 처리를 합니다. 만약 등록에 실패하면 실패 처리를 합니다. 인가 실패, 데이터 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/info-upload**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| restaurantImage | String | 식당 대표 사진 | O |
| restaurantName | String | 식당명 | O |
| restaurantFoodCategory | String | 식당 음식 카테고리 | O |
| restaurantLocation | String | 식당 주소 | O |
| restaurantTelNumber | String | 식당 연락처 |  |
| restaurantSnsAddress | String | 식당 SNS 주소 |  |
| restaurantOperationHours | String | 식당 운영 시간 |  |
| restaurantFeatures | String | 식당 특징 |  |
| restaurantNotice | String | 식당 공지 |  |
| restaurantRepresentativeMenu | String | 식당 대표 메뉴 |  |
| restaurantBusinessRegistrationNumber | String | 식당 등록자 사업자등록번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/restaurant/info-upload" \
 -H "Authorization: Bearer {JWT}" \ 
 -d "restaurantImage=https://www.rawshorts.com/freeicons/wp-content/uploads/2017/01/orange_travelpictdinner_1484336833.png" \ 
 -d "restaurantName=restaurant22" \ 
 -d "restaurantFoodCategory=한식" \ 
 -d "restaurantLocation=부산광역시 부산진구" \
 -d "restaurantTelNumber=051-000-0000" \
 -d "restaurantSnsAdress=https://blog.naver.com/yeohye99" \
 -d "restaurantOperationHours=09:00~18:00" \
 -d "restaurantFeatures=애견 동반 가능" \
 -d "restaurantNotice=일주일 간 서비스 제공" \
 -d "restaurantRepresentativeMenu=김치찌개" \
 -d "restaurantBusinessRegistrationNumber=222-22-22222"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 식당 정보 수정
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 식당 상세 정보(대표사진, 식당명, 음식카테고리, 우편번호, 주소, 연락처, sns주소, 운영시간, 특징, 공지, 대표메뉴)를 입력받고 수정에 성공하면 성공 처리를 합니다. 만약 수정에 실패하면 실패 처리를 합니다. 인가 실패, 데이터 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **PATCH**  
- URL : **/{restaurantId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| restaurantImage | String | 식당 대표 사진 | O |
| restaurantName | String | 식당명 | O |
| restaurantFoodCategory | String | 식당 음식 카테고리 | O |
| restaurantLocation | String | 식당 주소 | O |
| restaurantTelNumber | String | 식당 연락처 |  |
| restaurantSnsAddress | String | 식당 SNS 주소 |  |
| restaurantOperationHours | String | 식당 운영 시간 |  |
| restaurantFeatures | String | 식당 특징 |  |
| restaurantNotice | String | 식당 공지 |  |
| restaurantRepresentativeMenu | String | 식당 대표 메뉴 |  |

###### Example

```bash
curl -v -X PATCH "http://localhost:9999/api/v1/restaurant/${restaurantId}" \
 -H "Authorization: Bearer {JWT}" \ 
 -d "restaurantImage=https://www.rawshorts.com/freeicons/wp-content/uploads/2017/01/orange_travelpictdinner_1484336833.png" \ 
 -d "restaurantName=restaurant56" \ 
 -d "restaurantFoodCategory=양식" \ 
 -d "restaurantLocation=부산광역시 해운대구" \
 -d "restaurantTelNumber=051-000-1111" \
 -d "restaurantSnsAdress=https://blog.naver.com/yeohye99" \
 -d "restaurantOperationHours=09:00~14:00" \
 -d "restaurantFeatures=주차장 구비" \
 -d "restaurantNotice=금일 휴무" \
 -d "restaurantRepresentativeMenu=파스타" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 식당)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NR",
  "message": "No Exist Restaurant."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Review 모듈</h2>

식당 리뷰와 관련된 REST API 모듈  
  
- url : /api/v1/restaurant/review  

***

#### - 본인 리뷰 목록 불러오기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 사용자 본인의 리뷰 내역 리스트를 반환합니다. 만약 불러오기에 실패하면 실패 처리를 합니다. 인가 실패, 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/list**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/restaurant/review/list" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| reviewNumber | int | 리뷰 접수 번호 | O |
| reviewRestaurantId | String | 식당 아이디 | O |
| reviewRestaurantName | String | 식당명 | O |
| rating | double | 평점 | O |
| reviewDate | String | 리뷰 작성일 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.", 
  "reviewNumber": ${reviewNumber},
  "reviewRestaurantId": ${reviewRestaurantId},
  "reviewRestaurantName": ${reviewRestaurantName},
  "rating": ${rating},
  "reviewDate": ${reviewDate}
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 리뷰 상세 페이지 불러오기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 리뷰를 반환합니다. 만약 불러오기에 실패하면 실패 처리를 합니다. 인가 실패, 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/{reviewNumber}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/restaurant/review/${reviewNumber}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| reviewNumber | int | 리뷰 접수 번호 | O |
| reviewRestaurantId | String | 식당 아이디 | O |
| reviewRestaurantName | String | 식당명 | O |
| rating | double | 평점 | O |
| reviewImage | String | 리뷰 사진 | O |
| reviewContent | String | 리뷰 내용 | O |
| reviewDate | String | 리뷰 작성일 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.", 
  "reviewNumber": ${reviewNumber},
  "reviewRestaurantId": ${reviewRestaurantId},
  "reviewRestaurantName": ${reviewRestaurantName},
  "reviewImage": ${reviewImage},
  "rating": ${rating},
  "reviewDate": ${reviewDate},
  "reviewContent": ${reviewContent}
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 식당 리뷰 작성
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 리뷰 사진, 평점, 리뷰 내용을 입력받고 작성에 성공하면 성공 처리를 합니다. 만약 작성에 실패하면 실패 처리를 합니다. 인가 실패, 데이터 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/{restaurantId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| restaurantId | int | 식당 번호 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| reviewImage | String | 리뷰 사진 |  |
| rating | double | 평점 | O |
| reviewContents | String | 리뷰 내용 |  |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/restaurant/review/{restaurantId}" \
 -H "Authorization: Bearer {JWT}" \ 
 -d "reviewImage=https://www.rawshorts.com/freeicons/wp-content/uploads/2017/01/orange_travelpictdinner_1484336833.png" \ 
 -d "rating=3.5" \ 
 -d "reviewContents=식당이 깔끔하고 좋네요!" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 식당)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NR",
  "message": "No Exist Restaurant."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 식당 리뷰 수정
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 리뷰 사진, 평점, 리뷰 내용을 입력받고 수정에 성공하면 성공 처리를 합니다. 만약 수정에 실패하면 실패 처리를 합니다. 인가 실패, 데이터 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **PATCH**  
- URL : **/{reviewNumber}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| reviewNumber | int | 리뷰 접수 번호 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| reviewImage | String | 리뷰 사진 |  |
| rating | double | 평점 | O |
| reviewContents | String | 리뷰 내용 |  |

###### Example

```bash
curl -v -X PATCH "http://localhost:9999/api/v1/restaurant/review/{reviewNumber}" \
 -H "Authorization: Bearer {JWT}" \ 
 -d "reviewImage=https://www.rawshorts.com/freeicons/wp-content/uploads/2017/01/orange_travelpictdinner_1484336833.png" \ 
 -d "rating=4.0" \ 
 -d "reviewContents=별로였어요.." 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 식당)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NR",
  "message": "No Exist Restaurant."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 식당 리뷰 삭제
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 삭제에 성공하면 성공 처리를 합니다. 만약 삭제에 실패하면 실패 처리를 합니다. 인가 실패, 데이터 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **DELETE**  
- URL : **/{reviewNumber}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| reviewNumber | int | 리뷰 접수 번호 | O |

###### Example

```bash
curl -v -X DELETE "http://localhost:9999/api/v1/restaurant/review/{reviewNumber}" \
 -H "Authorization: Bearer {JWT}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 식당)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NR",
  "message": "No Exist Restaurant."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Reservation 모듈</h2>

식당 예약과 관련된 REST API 모듈  
  
- url : /api/v1/restaurant/reservation  

***

#### - 사용자 본인 예약 목록 불러오기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 사용자 본인의 예약 내역 리스트를 반환합니다. 만약 불러오기에 실패하면 실패 처리를 합니다. 인가 실패, 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/list**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/restaurant/reservation/list" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| restaurantReservationList | restaurantReservationListItem[] | 예약 내역 리스트 | O |

**RestaurantReservationListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| reservationNumber | int | 예약 접수 번호 | O |
| reservationStatus | boolean | 예약 상태 | O |
| reservationRestaurantId | int | 예약한 식당 번호 | O |
| reservationRestaurantName | String | 예약한 식당명 | O |
| reservationUserId | String | 예약한 사용자 아이디 | O |
| reservationUserName | String | 예약한 사용자 이름 | O |
| reservationDate | String | 예약 일자 | O |
| reservationTime | String | 예약 시간 | O |
| reservationPeople | int | 예약 인원 수 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.", 
  "restaurantReservationListItem": [
    {
      "reservationNumber": ${reservationNumber},
      "reservationStatus": ${reservationStatus},
      "reservationRestaurantId": ${reservationRestaurantId},
      "reservatoinRestaurantName": ${reservatoinRestaurantName},
      "reservationUserId": ${reservationUserId},
      "reservationUserName": ${reservationUserName},
      "reservationDate": ${reservationDate},
      "reservationTime": ${reservationTime},
      "reservationPeople": ${reservationPeople}
    }, ...
  ]
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 사장 본인 식당 예약 목록 불러오기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 사장 본인 식당의 예약 내역 리스트를 반환합니다. 만약 불러오기에 실패하면 실패 처리를 합니다. 인가 실패, 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/ceo-list**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/restaurant/reservation/ceo-list" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| restaurantReservationList | restaurantReservationListItem[] | 예약 내역 리스트 | O |

**RestaurantReservationListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| reservationNumber | int | 예약 접수 번호 | O |
| reservationStatus | boolean | 예약 상태 | O |
| reservationRestaurantId | int | 예약한 식당 번호 | O |
| reservationRestaurantName | String | 예약한 식당명 | O |
| reservationUserId | String | 예약한 사용자 아이디 | O |
| reservationUserName | String | 예약한 사용자 이름 | O |
| reservationDate | String | 예약 일자 | O |
| reservationTime | String | 예약 시간 | O |
| reservationPeople | int | 예약 인원 수 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.", 
  "restaurantReservationListItem": [
    {
      "reservationNumber": ${reservationNumber},
      "reservationStatus": ${reservationStatus},
      "reservationRestaurantId": ${reservationRestaurantId},
      "reservatoinRestaurantName": ${reservatoinRestaurantName},
      "reservationUserId": ${reservationUserId},
      "reservationUserName": ${reservationUserName},
      "reservationDate": ${reservationDate},
      "reservationTime": ${reservationTime},
      "reservationPeople": ${reservationPeople}
    }, ...
  ]
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 식당 예약 등록
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 날짜, 시간, 인원수를 입력받고 요청을 보내면 예약에 성공했을 경우 성공 처리를 합니다. 만약 예약에 실패하면 실패 처리를 합니다. 인가 실패, 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/{restaurantId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| restaurantId | int | 식당 번호 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| reservationDate | String | 예약 일자 | O |
| reservationTime | String | 예약 시간 | O |
| reservationPeople | int | 예약 인원 수 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/restaurant/reservation/{restaurantId}" \
 -H "Authorization: Bearer {JWT}" \ 
 -d "reservationDate=2024-06-26" \ 
 -d "reservationTime=18:30" \ 
 -d "reservationPeople=3" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 식당)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NR",
  "message": "No Exist Restaurant."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 식당 예약 취소
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 예약 취소에 성공하면 성공 처리를 합니다. 만약 실패하면 실패 처리를 합니다. 인가 실패, 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **DELETE**  
- URL : **/{restaurantId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| restaurantId | int | 식당 번호 | O |

###### Example

```bash
curl -v -X DELETE "http://localhost:9999/api/v1/restaurant/reservation/{restaurantId}" \
 -H "Authorization: Bearer {JWT}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 식당)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NR",
  "message": "No Exist Restaurant."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Favorite 모듈</h2>

식당 찜과 관련된 REST API 모듈  
  
- url : /api/v1/restaurant/favorite  

***

#### - 본인 찜 목록 불러오기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 사용자 본인의 찜 내역 리스트를 반환합니다. 만약 불러오기에 실패하면 실패 처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/list**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/restaurant/favorite/list" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| favoriteUserEmailId | String | 사용자 아이디 | O |
| restaurantList | restaurantListItem[] | 식당 리스트 | O |

**RestaurantListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| restaurantId | int | 식당 번호 | O |
| restaurantImage | String | 식당 대표 사진 | O |
| restaurantName | String | 식당 이름 | O |
| restaurantFoodCateogory | String | 식당 음식 카테고리 | O |
| restaurantLocation | String | 식당 주소 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "favoriteUserEmailId": ${favoriteUserEmailId},
  "restaurantListItem": [
    {
      "restaurantId": ${restaurantId},
      "restaurantImage": ${restaurantImage},
      "restaurantName": ${restaurantName},
      "restaurantFoodCateogory": ${restaurantFoodCateogory},
      "restaurantLocation": ${restaurantLocation}
    }, ...
  ]
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 식당 찜 등록
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 식당 아이디를 입력받고 찜(저장)에 성공하면 성공 처리를 합니다. 만약 실패하면 실패 처리를 합니다. 인가 실패, 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/{restaurantId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| restaurantId | int | 식당 번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/restaurant/favorite/{restaurantId}" \
 -H "Authorization: Bearer {JWT}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 식당)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NR",
  "message": "No Exist Restaurant."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***
<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Inquiry Board 모듈</h2>

문의와 관련된 REST API 모듈 
  
- url : /api/v1/inquiry-board  

***

#### - 문의 목록 불러오기
  
##### 설명

작성일 기준 내림차순으로 문의 게시물 리스트를 반환합니다. 만약 불러오기에 실패하면 실패 처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/list**  

##### Request

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/inquiry-board/list" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| inquiryBoardList | inquiryBoardListItem[] | 문의 내역 게시물 리스트 | O |

**InquiryBoardListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| inquiryNumber | int | 문의 번호 | O |
| status | boolean | 문의 상태 | O |
| inquiryPublic | boolean | 문의 공개 여부 | O |
| inquiryTitle | String | 문의 제목 | O |
| inquiryWriterId | String | 문의 작성자 아이디 | O |
| inquiryWriterNickname | String | 문의 작성자 닉네임 | O |
| inquiryWriteDatetime | String |문의 작성일</br>(yy.mm.dd 형태) | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "inquiryBoardList": [
    {
      "inquiryNumber": ${inquiryNumber},
      "status": ${status},
      "inquiryPublic": ${inquiryPublic},
      "inquiryTitle": ${inquiryTitle},
      "inquiryWriterId": ${inquiryWriterId},
      "inquiryWriterNickname": ${inquiryWriterNickname},
      "inquiryWriteDatetime": ${inquiryWriteDatetime}
    }, ...
  ]
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의 검색 목록 불러오기
=======
  
##### 설명

클라이언트로부터 검색어를 입력받고 요청을 보내면 작성일 기준 내림차순으로 제목에 해당 검색어가 포함된 문의 게시물 리스트를 반환합니다. 만약 불러오기에 실패하면 실패 처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/list/search**  

##### Request

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| searchWord | String | 검색어 | O |

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/inquiry-board/list/search?word=${searchWord}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| inquiryBoardList | inquiryBoardListItem[] | 문의 내역 게시물 리스트 | O |

**InquiryBoardListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| inquiryNumber | int | 문의 번호 | O |
| status | boolean | 문의 상태 | O |
| inquiryPublic | boolean | 문의 공개 여부 | O |
| inquiryTitle | String | 문의 제목 | O |
| inquiryWriterId | String | 문의 작성자 아이디 | O |
| inquiryWriterNickname | String | 문의 작성자 닉네임 | O |
| inquiryWriteDatetime | String | 문의 작성일</br>(yy.mm.dd 형태) | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "inquiryBoardList": [
    {
      "inquiryNumber": ${inquiryNumber},
      "status": ${status},
      "inquiryPublic": ${inquiryPublic},
      "inquiryTitle": ${inquiryTitle},
      "inquiryWriterId": ${inquiryWriterId},
      "inquiryWriterNickname": ${inquiryWriterNickname},
      "inquiryWriteDatetime": ${inquiryWriteDatetime}
    }, ...
  ]
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 나의 문의 목록 불러오기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 요청을 보내면 작성자의 아이디와 일치하는 게시물을 작성일 기준 내림차순으로 게시물 리스트를 반환합니다. 만약 불러오기에 실패하면 실패 처리를 합니다. 인가 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/my-list**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/inquiry-board/my-list" \
 -H "Authorization: Bearer {JWT}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| inquiryBoardList | inquiryBoardListItem[] | 문의 내역 게시물 리스트 | O |

**InquiryBoardListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| inquiryNumber | int | 문의 번호 | O |
| status | boolean | 문의 상태 | O |
| inquiryPublic | boolean | 문의 공개 여부 | O |
| inquiryTitle | String | 문의 제목 | O |
| inquiryWriterId | String | 문의 작성자 아이디 | O |
| inquiryWriterNickname | String | 문의 작성자 닉네임 | O |
| inquiryWriteDatetime | String | 문의 작성일</br>(yy.mm.dd 형태) | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "inquiryBoardList": [
    {
      "inquiryNumber": ${inquiryNumber},
      "status": ${status},
      "inquiryPublic": ${inquiryPublic},
      "inquiryTitle": ${inquiryTitle},
      "inquiryWriterId": ${inquiryWriterId},
      "inquiryWriterNickname": ${inquiryWriterNickname},
      "inquiryWriteDatetime": ${inquiryWriteDatetime}
    }, ...
  ]
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의 상세 페이지 불러오기
  
##### 설명

문의 번호에 해당하는 게시물을 반환합니다. 만약 불러오기에 실패하면 실패 처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/{inquiryNumber}**  

##### Request

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| inquiryNumber | int | 문의 번호 | O |

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/inquiry-board/${inquiryNumber}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| inquiryNumber | int | 문의 번호 | O |
| status | boolean | 문의 상태 | O |
| inquiryPublic | boolean | 문의 공개 여부 | O |
| inquiryTitle | String | 문의 제목 | O |
| inquiryWriterId | String | 작성자 아이디 | O |
| inquiryWriterNickname | String | 문의 작성자 닉네임 | O |
| inquiryWriteDatetime | String | 작성일</br>(yyyy.mm.dd 형태) | O |
| inquiryContents | String | 문의 내용 | O |
| inquiryComment | String | 답글 내용 |  |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "inquiryNumber": ${inquiryNumber},
  "status": ${status},
  "inquiryPublic": ${inquiryPublic},
  "inquiryTitle": ${inquiryTitle},
  "inquiryWriterId": ${inquiryWriterId},
  "inquiryWriterNickname": ${inquiryWriterNickname},
  "inquiryWriteDatetime": ${inquiryWriteDatetime},
  "inquiryContents": ${inquiryContents},
  "inquiryComment": ${inquiryComment}
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의 작성
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 제목, 내용을 입력받고 작성에 성공하면 성공 처리를 합니다. 만약 작성에 실패하면 실패 처리 됩니다. 인가 실패, 데이터 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| inquiryTitle | String | 제목 | O |
| inquiryContents | String | 내용 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/inquiry-board/" \
 -H "Authorization: Bearer {JWT}" \
 -d "inquiryTitle=test" \
 -d "inquiryContents=test Contents"

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의 답글 작성
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 문의 번호와 답글 내용을 입력받고 요청을 보내면 해당하는 게시물의 답글이 작성됩니다. 만약 증가에 실패하면 실패 처리를 합니다. 인가 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/{inquiryNumber}/comment**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| inquiryNumber | int | 문의 번호 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| comment | string | 답글 내용 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/inquiry-board/${inquiryNumber}/comment" \
 -H "Authorization: Bearer {JWT}" \
 -d "comment=test comment"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (이미 작성된 답글)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "WC",
  "message": "Written Comment."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의 수정
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 문의 번호, 제목, 내용을 입력받고 수정에 성공하면 성공 처리를 합니다. 만약 수정에 실패하면 실패 처리 됩니다. 인가 실패, 데이터베이스 에러, 데이터 유효성 검사 실패가 발생할 수 있습니다.

- method : **PATCH**  
- URL : **/update/{inquiryNumber}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| inquiryNumber | int | 수정할 문의 번호 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| inquiryTitle | String | 문의 제목 | O |
| inquiryContents | String | 문의 내용 | O |

###### Example

```bash
curl -v -X PATCH "http://localhost:9999/api/v1/inquiry-board/update/{inquiryNumber}" \
 -H "Authorization: Bearer {JWT}" \
 -d "inquiryTitle=test update" \
 -d "inquiryContents=test contents update"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (답변 완료된 게시물)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "WC",
  "message": "Written Comment."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의 삭제
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 문의 번호를 입력받고 요청을 보내면 해당하는 게시물이 삭제됩니다. 만약 삭제에 실패하면 실패 처리를 합니다. 인가 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **DELETE**  
- URL : **/{inquiryNumber}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| inquiryNumber | int | 문의 번호 | O |

###### Example

```bash
curl -v -X DELETE "http://localhost::9999/api/v1/inquiry-board/${inquiryNumber}" \
 -H "Authorization: Bearer {JWT}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

***
<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Notice Board 모듈</h2>

공지와 관련된 REST API 모듈 
  
- url : /api/v1/notice-board  

***

#### - 공지 목록 불러오기
  
##### 설명

작성일 기준 내림차순으로 공지 게시물 리스트를 반환합니다. 만약 불러오기에 실패하면 실패 처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다

- method : **GET**  
- URL : **/list**  

##### Request

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/notice-board/list" 
=======
**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 나의 문의 내역 리스트 불러오기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 요청을 보내면 작성자의 아이디와 일치하는 게시물을 작성일 기준 내림차순으로 게시물 리스트를 반환합니다. 만약 불러오기에 실패하면 실패 처리를 합니다. 인가 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/my-list**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Example

```bash
curl -v -X GET "http:////localhost:9999/api/v1/inquiry-board/my-list" \" \
 -H "Authorization: Bearer {JWT}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| noticeBoardList | noticeBoardListItem[] | 공지 게시물 리스트 | O |

**NoticeBoardListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeNumber | int | 공지 번호 | O |
| noticeTitle | String | 공지 제목 | O |
| noticeWriterId | String | 공지 작성자 아이디 | O |
| noticeWriterNickname | String | 공지 작성자 닉네임 | O |
| noticeWriteDatetime | String | 공지 작성일(yy.mm.dd 형태) | O |
| viewCount | int | 조회수 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "noticeBoardList": [
    {
      "noticeNumber": ${noticeNumber},
      "noticeTitle": ${noticeTitle},
      "noticeWriterId": ${noticeWriterId},
      "noticeWriterNickname": ${noticeWriterNickname},
      "noticeWriteDatetime": ${noticeWriteDatetime},
      "viewCount":${viewCount}
    }, ...
  ]
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 공지 검색 목록 불러오기
  
##### 설명

검색어를 입력받고 요청을 보내면 작성일 기준 내림차순으로 제목에 해당 검색어가 포함된 게시물 리스트를 반환합니다. 만약 불러오기에 실패하면 실패 처리를 합니다. 인가 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/list/search**  

##### Request

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| searchWord | String | 검색어 | O |

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/notice-board/list/search?word=${searchWord}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| noticeBoardList | noticeBoardListItem[] | 공지 게시물 리스트 | O |

**NoticeBoardListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeNumber | int | 공지 번호 | O |
| noticeTitle | String | 공지 제목 | O |
| noticeWriterId | String | 공지 작성자 아이디 | O |
| noticeWriterNickname | String | 공지 작성자 닉네임 | O |
| noticeWriteDatetime | String | 공지 작성일(yy.mm.dd 형태) | O |
| viewCount | int | 조회수 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "noticeBoardList": [
    {
      "noticeNumber": ${noticeNumber},
      "noticeTitle": ${noticeTitle},
      "noticeWriterId": ${noticeWriterId},
      "noticeWriterNickname": ${noticeWriterNickname},
      "noticeWriteDatetime": ${noticeWriteDatetime},
      "viewCount":${viewCount}
    }, ...
  ]
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 공지 상세 페이지 불러오기
  
##### 설명

공지 번호에 해당하는 공지 게시물 데이터를 반환합니다. 만약 불러오기에 실패하면 실패 처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/{noticeNumber}**  

##### Request

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeNumber | int | 공지 번호 | O |

###### Example

```bash
curl -v -X GET "http://localhost:9999/api/v1/notice-board/${noticeNumber}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| noticeNumber | int | 공지 번호 | O |
| noticeTitle | String | 공지 제목 | O |
| noticeWriteId | String | 작성자 아이디 | O |
| noticeWriterNickname | String | 작성자 닉네임 | O |
| noticeWriteDatetime | String | 작성일</br>(yyyy.mm.dd 형태) | O |
| noticeContents | String | 공지 내용 | O |
| viewCount | int | 조회수 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "noticeNumber": ${noticeNumber},
  "noticeTitle": ${noticeTitle},
  "noticeWriteId": ${noticeWriteId},
  "noticeWriterNickname": ${noticeWriterNickname},
  "noticeWriteDatetime": ${noticeWriteDatetime},
  "noticeContents": ${noticeContents},
  "viewCount": ${viewCount}
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 공지 작성
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 제목, 내용을 입력받고 작성에 성공하면 성공 처리를 합니다. 만약 작성에 실패하면 실패 처리 됩니다. 인가 실패, 데이터 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeTitle | String | 공지 제목 | O |
| noticeContents | String | 공지 내용 | O |

###### Example

```bash
curl -v -X POST "http://localhost:9999/api/v1/notice-board/" \
 -H "Authorization: Bearer {JWT}" \
 -d "noticeTitle=test" \
 -d "noticeContents=test notice contents"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 공지 게시물 작성
  
##### 설명

공지 번호에 해당하는 공지 게시물의 조회수를 증가합니다. 만약 증가에 실패하면 실패 처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/**  

##### Request

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeTitle | String | 공지 제목 | O |
| noticeContents | String | 공지 내용 | O |

###### Example

```bash
curl -v -X POST "http://localhost::9999/api/v1/notice-board/{noticeNumber}/increase-view-count" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 공지 수정
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 접수 번호, 제목, 내용을 입력받고 수정에 성공하면 성공 처리를 합니다. 만약 수정에 실패하면 실패 처리 됩니다. 인가 실패, 데이터 유효성 검사 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **PATCH**  
- URL : **/update/{noticeNumber}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeNumber | int | 수정할 공지 번호 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeTitle | String | 공지 제목 | O |
| noticeContents | String | 공지 내용 | O |

###### Example

```bash
curl -v -X PATCH "http://localhost:9999/api/v1/notice-board/update/{noticeNumber}" \
 -H "Authorization: Bearer {JWT}" \
 -d "noticeTitle=test update" \
 -d "noticeContents=test notice contents update"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 공지 삭제

##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 접수 번호를 입력받고 요청을 보내면 해당하는 공지 게시물이 삭제됩니다. 만약 삭제에 실패하면 실패 처리를 합니다. 인가 실패, 데이터베이스 에러가 발생할 수 있습니다.

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeNumber | int | 공지 번호 | O |

###### Example

```bash
curl -v -X DELETE "http://localhost::9999/api/v1/notice-board/${noticeNumber}" \
 -H "Authorization: Bearer {JWT}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 공지 게시물 삭제

##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 접수 번호를 입력받고 요청을 보내면 해당하는 공지 게시물이 삭제됩니다. 만약 삭제에 실패하면 실패 처리를 합니다. 인가 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **DELETE**  
- URL : **/{noticeNumber}** 

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeNumber | int | 공지 번호 | O |

###### Example

```bash
curl -v -X DELETE "http://localhost::9999/api/v1/notice-board/${noticeNumber}" \
 -H "Authorization: Bearer {JWT}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***