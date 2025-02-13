# 診所預約系統 (Appointment System)

## 簡介 (Introduction)

本專案使用 Java Swing (JFrame) 開發，旨在提供一個簡單易用的診所預約系統。系統提供預約、查詢、更改、取消預約等功能，方便患者管理自己的就診安排。

## 功能特色 (Features)

*   **註冊功能**: 使用者可自訂信箱、密碼、姓名及電話進行註冊，信箱、密碼、及電話須符合格式。
*   **密碼找回**: 使用者可透過信箱或是電話找回註冊的密碼。
*   **登入功能**: 使用者可透過信箱或是電話作為帳號登入系統。
*   **更改功能**: 使用者可更改自己的個人資訊。
*   **註銷功能**: 使用者可註銷已註冊的帳號。
*   **預約功能**: 患者可選擇日期、時段及醫生進行預約。
*   **查詢功能**: 患者可查詢自己的預約紀錄。
*   **列印功能**: 患者可將查詢到的預約紀錄列印成PDF。
*   **更改預約**: 患者可更改自己的預約紀錄。
*   **取消預約**: 患者可取消已預約的行程。


## 安裝與執行 (Installation and Execution)

1.  **環境需求**:
    *   Java Development Kit (JDK) 11 
    *   Eclipse IDE

2.  **下載專案**:
    選擇"MedicalAppointment"文件下載。

3.  **匯入專案**:
    在 Eclipse IDE 中，選擇 File > Import，在彈出的導入向導中，選擇 General > Existing Projects into Workspace，然後點擊 Next。
    選擇 Select root directory 或 Select archive file，然後點擊 Browse 來定位包含專案的目錄或檔案。在 Projects 下選擇要匯入的專案。點擊 Finish。

4.  **編譯與執行**:
    在 Eclipse IDE 中，找到 LoginUI.java 文件。右鍵點擊 Main.java，選擇 Run As > Java Application。

    

## 使用方法 (How to Use)

*   開啟程式後，根據介面指示進行註冊、登入、預約、查詢、更改或取消預約等操作。


## 專案結構 (Project Structure)

<pre>
診所預約系統
MedicalAppointment/
├── src/
│   ├── main/java/
│   │   ├── controller/
│   │   │   ├── AppointmentManageUI.java
│   │   │   ├── AppointmentUI.java
│   │   │   ├── UserManageUI.java
│   │   │   ├── ForgetPasswordUI.java
│   │   │   ├── LoginUI.java
│   │   │   └── RegisterUI.java
│   │   ├── dao/
│   │   │   ├── AppointmentDao.java
│   │   │   ├── UserDao.java
│   │   │   ├── AppointmentDaoImpl.java
│   │   │   └── UserDaoImpl.java
│   │   ├── model/
│   │   │   ├── Appointment.java
│   │   │   └── User.java
│   │   ├── service/
│   │   │   ├── AppointmentService.java
│   │   │   ├── UserService.java
│   │   │   ├── AppointmentServiceImpl.java
│   │   │   └── UserServiceImpl.java
│   │   └── util/
│   │   │   ├── DbConnection.java
│   │   │   └── FormatTool.java
├── Referenced Libraries/
│   ├─ clockRun.jar
│   ├─ IO.jar
│   ├─ jcalendar-1.4.jar
│   ├─ mysql-connector-j-8.0.33.jar
└── mysql/
</pre>


