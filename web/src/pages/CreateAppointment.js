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
        const identity = await this.client.getIdentity();
        this.dataStore.set("email", identity.email);
        document.getElementById("client").setAttribute('placeholder', 'Client');
        document.getElementById("dateTime").setAttribute('placeholder', 'Date/Time');
        document.getElementById("pet").setAttribute('placeholder', 'Pet');
        document.getElementById("service").setAttribute('placeholder', 'Service');

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
        const createButton = document.getElementById('submit');
        const client = document.getElementById('client').value || document.getElementById('client').getAttribute('placeholder');
        const dateTime = document.getElementById('dateTime').value ||  document.getElementById('dateTime').getAttribute('placeholder');
        const pet = document.getElementById('pet').value ||  document.getElementById('pet').getAttribute('placeholder');
        const service = document.getElementById('service').value ||  document.getElementById('service').getAttribute('placeholder');
        console.log(client, dateTime, pet, service);
        try {
            // Check that the user is authenticated
            const user = await this.client.getIdentity();
            if (!user) {
                // If not authenticated, show error message
                throw new Error('Only authenticated users can create an appointment.');
            }
            const apptCreator = user.email;
            const event = await this.client.createAppointment(client, dateTime, pet, service,
                (error) => {
                });
                console.log(client, dateTime, pet, service);
            window.location.href ='/AllAppointments.html';
            } catch (error) {
            }
    }

        confirmRedirect() {
            window.location.href = '/AllAppointments.html';
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
        async logout(){
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