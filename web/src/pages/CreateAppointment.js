import petproClient from '../api/petproClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/header';
import DataStore from "../util/DataStore";

class CreateAppointment extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount','confirmRedirect','submitFormData', 'redirectUpdateClientProfile','redirectAllClientProfiles',
        'redirectCreateAppointment','redirectAllAppointments', 'redirectServiceMenu', 'logout'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        // const urlParams = new URLSearchParams(window.location.search);
        const identity = await this.client.getIdentity();
        this.dataStore.set('id', identity.email);
        const appointment = await this.client.createAppointment(client, dateTime, pet, service);
        this.dataStore.set('appointment', appointment);
        if(appointment == null) {
            document.getElementById("welcome").innerText = "Please log in or sign up to create Appointments."
        }
        document.getElementById("client").setAttribute('placeholder', 'Client');
        document.getElementById("dateTime").setAttribute('placeholder', 'Date/Time');
        document.getElementById("pet").setAttribute('placeholder', 'Pet');
        document.getElementById("service").setAttribute('placeholder', 'Service');
        this.setPlaceholders();

    }

    mount() {

        document.getElementById('AllAppointments').addEventListener('click', this.redirectAllAppointments);
        document.getElementById('ServiceMenu').addEventListener('click', this.redirectGetServiceMenu);
        document.getElementById('AllClientProfiles').addEventListener('click', this.redirectAllClientProfiles);
        document.getElementById('CreateClientProfile').addEventListener('click', this.redirectCreateClientProfile);
        document.getElementById('logout').addEventListener('click', this.logout);
        document.getElementById('submit').addEventListener('click', this.submitFormData);


        this.client = new petproClient();
        this.clientLoaded();
    }


    async submitFormData(evt){
        evt.preventDefault();
        const client = document.getElementById('client').value || document.getElementById('client').getAttribute('placeholder');
        const dateTime = document.getElementById('dateTime').value ||  document.getElementById('dateTime').getAttribute('placeholder');
        const pet = document.getElementById('pet').value ||  document.getElementById('pet').getAttribute('placeholder');
        const service = document.getElementById('service').value ||  document.getElementById('service').getAttribute('placeholder');
        console.log(client, dateTime, pet, service);
        let profile;
        if(document.getElementById('welcome').innerText == "Please log in or sign up to create Appointments."){
            appointment = await this.client.createAppointment(client, dateTime, pet, service, (error) => {
                errorMessageDisplay.innerText = `Error: ${error.message}`;
            });
        } else {
            appointment = await this.client.updateAppointment(this.dataStore.get('id'), client, dateTime, pet, service, (error) => {
                errorMessageDisplay.innerText = `Error: ${error.message}`;
            });
        }


        this.dataStore.set('appointment', appointment);
        document.getElementById('client').innerText = client || appointment.appointmentModel.client;
        document.getElementById('dateTime').innerText = dateTime || appointment.appointmentModel.dateTime;
        document.getElementById('pet').innerText = pet || appointment.appointmentModel.pet;
        document.getElementById('service').innerText = service || appointment.appointmentModel.service;
        document.getElementById('loading-modal').remove();

    }
        confirmRedirect() {
            window.location.href = '/ClientProfile.html';
        }
        redirectUpdateClientProfile(){
            window.location.href = '/UpdateClientProfile.html';
        }
        redirectAllClientProfiles(){
            window.location.href = '/AllClientProfiles.html';
        }
        redirectCreateAppointment(){
            window.location.href = '/CreateAppointment.html';
        }
        redirectAllAppointments(){
            window.location.href = '/AllAppointments.html';
        }
        redirectServiceMenu(){
            window.location.href = '/ServiceMenu.html';
        }
        logout(){
            this.client.logout();
        }
}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createAppointment = new CreateAppointment();
    createAppointment.mount();
};

window.addEventListener('DOMContentLoaded', main);