## 1. Problem Statement

[PetPro Scheduler] is an application designed to assist a busy pet groomer in keeping his days organized and flowing well.

This design document describes PetPro Scheduler, which will allow the user to create and manage appointments, keep client profiles, and maintain a menu of services.

## 2. Top Questions to Resolve in Review

1. As this project will expand beyond this sprint, what can reasonably be finished before the deadline?


## 3. Use Cases

U1. As a user, I want to be able to create a new client profile.

U2. As a user, I want to be able to edit a client profile.

U3. As a user, I want to be able to delete a client profile.

U4. As a user, I want to be able to view all profiles in alphabetical order.

U5. As a user, I want to be able to view one specific profile.

U6. As a user, I want to be able to create a new appointment.

U7. As a user, I want to be able to delete an appointment.

U8. As a user, I want to be able to view all appointments.

U9. As a user, I want to be able to create a menu of services.

U10. As a user, I want to be able to edit my menu of services.

## 4. Project Scope

### 4.1. In Scope

* Creating, retrieving, and updating a service menu
* creating and retrieving appointments
* creating, retrieving, and updating client profiles

### 4.2. Out of Scope

* Processing and tracking payments
* Sending alerts and appointment reminders
* Maintaining different groomer profiles
* Modifying levels of access for different users

## 5. Proposed Architecture Overview

This initial iteration will provide the minimum lovable product (MLP) including
creating, retrieving, and updating a client profile; creating, retrieving, and updating an appointment; as well as adding to, editing, and
retrieving a service menu.

We will use API Gateway and Lambda to create 8 endpoints (`GetClientProfile`,
`CreateClientProfile`, `UpdateClientProfile`, `GetAllClientProfiles`,
`CreateAppointment`, `GetAllAppointments`, and
`GetServiceMenu` and `UpdateServiceMenu`)
that will handle the creation, update, and retrieval of profiles, appointments, and the service menu to satisfy our
requirements.

We will store appointments in a table in DynamoDB. Services
themselves will also be stored in DynamoDB. Client Profiles will also be stored in a DynamoDB table.

PetPro Scheduler will also provide a web interface for the user to manage
appointments. A main page providing a list view of all appointments
will let them create new appointments and link off to pages for the service menu and client profiles to update
metadata and add clients.

## 6. API

### 6.1. Public Models

```
// ProfileModel

String id;
String name;
String phone;
String address;
List<String> notes;
List<String> pets;
```

```
// AppointmentModel

string appointmentId;
String client;
DateTime dateTime;
String pet;
String service;
```

### 6.2. Get Profile Endpoint

* Accepts `GET` requests to `/clientprofiles/:id`
* Accepts a clientID and returns the corresponding ClientProfileModel.
    * If the given clientID is not found, will throw a
      `ProfileNotFoundException`

### 6.3. Create Profile Endpoint

* Accepts `POST` requests to `/clientprofiles`
* Accepts data to create a new profile with a provided name, a phone number, a list of pets, and optional notes. Returns the new profile, including a unique
  clientID.
* For security concerns, we will validate the provided profile name does not
  contain any invalid characters: `" ' \`
    * If the playlist name contains any of the invalid characters, will throw an
      `InvalidAttributeValueException`.

### 6.4. Update Profile Endpoint

* Accepts `PUT` requests to `/clientprofiles/:id`
* Accepts data to update a profile including a clientID, an updated profile
  name, an updated phone number, updated notes, and updated list of pets. Returns the updated
  profile.
    * If the clientID is not found, will throw a `ProfileNotFoundException`
* For security concerns, we will validate the provided profile name does not
  contain invalid characters: `" ' \`
    * If the profile name contains invalid characters, will throw an
      `InvalidAttributeValueException`

![Client sends submit profile update form to Website Profile page. Website
profile page sends an update request to UpdateProfileActivity.
UpdateProfileActivity saves updates to the profiles
database.](images/example_design_document/UpdatePlaylistSD.png)

### 6.5. Delete Profile Endpoint

* Accepts `DELETE` requests to `/clientprofiles/:id/profile`
* Accepts a clientID to be deleted.
    * If the clientID is not found, will throw a `ProfileNotFoundException`


![Client submits the delete profile form to the Website profile page. The website
profile page sends a delete profile request to the DeleteProfileActivity. The
DeleteProfileActivity removes the profile from the profiles
database.](images/example_design_document/AddSongSD.png)

### 6.6. Get All Profiles Endpoint

* Accepts `GET` requests to `/clientprofiles/`
* Retrieves all profiles
    * Returns the profile list in alphabetical order
* If no profiles are found, will return an empty list


### 6.7. Create Appointment Endpoint

* Accepts `POST` requests to `/appointments`
* Accepts data to create a new appointment with a provided client name, a given date and time,
  a pet, and the service(s) to be performed. Returns the new appointment with a given appointmentId.


### 6.8. Delete Appointment Endpoint

* Accepts `DELETE` requests to `/appointments/:id/appointment`
* Accepts a appointmentID to be deleted.
    * If the appointmentID is not found, will throw an `AppointmentNotFoundException`


![Client submits the delete appointment form to the Website appointments page. The website
appointments page sends a delete appointment request to the DeleteAppointmentActivity. The
DeleteAppointmentActivity removes the appointment from the appointments
database.](images/example_design_document/AddSongSD.png)


### 6.9. Get Appointments Endpoint

* Accepts `GET` requests to `/appointments/`
* Retrieves all appointments
    * Returns the appointment list in chronological order
* If no appointments are found, will return an empty list



//ServiceMenuModel

Map<String, Double> pricedServices;

### 7.2 `profiles`

```
id // partition key, string
name // sort key, string
phone // string
address // string
notes // list
pets // list
```


### 7.2. `appointments`

```
id // partition key, string
dateTime // sort key, string
client // string
pet // string
service // string
```

### 7.2. `services`

```
service // partition key, string
description // string
```

## 8. Pages

