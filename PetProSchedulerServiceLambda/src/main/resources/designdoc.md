## 1. Problem Statement

[PetPro Scheduler] is an application designed to assist a busy pet groomer in keeping his days organized and flowing well.

This design document describes PetPro Scheduler, which will allow the user to create and manage appointments, keep client profiles, and maintain a menu of services.

## 2. Top Questions to Resolve in Review

1. As this project will expand beyond this sprint, what can reasonably be finished before the deadline?


## 3. Use Cases

U1. As a user, I want to be able to create a new client profile.

U2. As a user, I want to be able to edit a client profile.

U3. As a user, I want to be able to delete a client profile.

U4. As a user, I want to be able to view all client profiles.

U5. As a user, I want to be able to view one specific profile.

U6. As a user, I want to be able to create a new appointment.

U7. As a user, I want to be able to delete an appointment.

U8. As a user, I want to be able to view all appointments.

U9. As a user, I want to be able to create a menu of services.

U10. As a user, I want to be able to edit my menu of services.


## Known Bugs
* Appointment date and time need to be adjusted from type String to type DateTime and sorted in the appointments table accordingly.
* Client profiles should be sorted alphabetically when we're viewing all client profiles.

## Please see README for future goals.

## 4. Project Scope

### 4.1. In Scope

* Creating, retrieving, deleting from, and updating a service menu
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

We will use API Gateway and Lambda to create 11 endpoints (`GetClientProfile`,
`CreateClientProfile`, `UpdateClientProfile`, `GetAllClientProfiles`,
`CreateAppointment`, `GetAllAppointments`, `DeleteAppointment`,
`GetServiceMenu`, `DeleteService`, `UpdateService`, and `CreateService`)
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

String appointmentId;
String client;
String dateTime;
String pet;
String service;
```

### 6.2. Get Client Profile Endpoint

* Accepts `GET` requests to `/clientprofiles/:id`
* Accepts a clientID and returns the corresponding ClientProfileModel.
    * If the given clientID is not found, will throw a
      `ClientProfileNotFoundException`

### 6.3. Create Client Profile Endpoint

* Accepts `POST` requests to `/clientprofiles`
* Accepts data to create a new profile with a provided name, a phone number, a list of pets, and optional notes. Returns the new profile, including a unique
  ID.
* For security concerns, we will validate the provided profile name does not
  contain any invalid characters: `" ' \`
    * If the playlist name contains any of the invalid characters, will throw an
      `InvalidAttributeValueException`.

### 6.4. Update Profile Endpoint

* Accepts `PUT` requests to `/clientprofiles/:id`
* Accepts data to update a profile including a clientID, an updated profile
  name, an updated phone number, updated notes, and updated list of pets. Returns the updated
  profile.
    * If the clientID is not found, will throw a `ClientProfileNotFoundException`
* For security concerns, we will validate the provided profile name does not
  contain invalid characters: `" ' \`
    * If the profile name contains invalid characters, will throw an
      `InvalidAttributeValueException`


### 6.5. Delete Profile Endpoint

* Accepts `DELETE` requests to `/clientprofiles/:id`
* Accepts a clientID to be deleted.
    * If the clientID is not found, will throw a `ClientProfileNotFoundException`



### 6.6. Get All Profiles Endpoint

* Accepts `GET` requests to `/clientprofiles/`
* Retrieves all profiles
    * Returns the profile list
* If no profiles are found, will return an empty list


### 6.7. Create Appointment Endpoint

* Accepts `POST` requests to `/appointments`
* Accepts data to create a new appointment with a provided client name, a given date and time,
  a pet, and the service(s) to be performed. Returns the new appointment with a given appointmentId.


### 6.8. Delete Appointment Endpoint

* Accepts `DELETE` requests to `/appointments/:id`
* Accepts a appointmentID to be deleted.
    * If the appointmentID is not found, will throw an `AppointmentNotFoundException`



### 6.9. Get Appointments Endpoint

* Accepts `GET` requests to `/appointments/`
* Retrieves all appointments
    * Returns the appointment list in chronological order
* If no appointments are found, will return an empty list


```
//ServiceMenuModel

String title
String description
```
### 7.0. Get Service Menu Endpoint

* Accepts `GET` requests to `/services`
* Retrieves all services
* If no services are found, will return an empty list



### 7.1. Delete Service Endpoint

* Accepts `DELETE` requests to `/services/:title`
* Accepts a title to be deleted.



### 7.2. Create Service Endpoint

* Accepts `POST` requests to `/services`
* Accepts data to create a new service with a provided title and description. Returns the new service.



### 7.3. Update Service Endpoint

* Accepts `PUT` requests to `/services/:title`
* Accepts data to update a service including a title and description. Returns the updated
  service.


### 7.4 `profiles`

```
id // partition key, string
name // sort key, string
phone // string
address // string
notes // list
pets // list
```


### 7.5. `appointments`

```
id // partition key, string
dateTime // sort key, string
client // string
pet // string
service // string
```

### 7.6. `services`

```
title // partition key, string
description // string
```

## 8. Pages

