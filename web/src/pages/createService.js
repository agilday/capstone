import petproClient from '../api/petproClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/dannaHeader';
import DataStore from "../util/DataStore";

class createService extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount','confirmRedirect','submitFormData', 'redirectEditClientProfile','redirectAllClientProfiles',
        'redirectCreateAppointment','redirectAllAppointments', 'redirectServiceMenu', 'logout','setPlaceholders'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        // const urlParams = new URLSearchParams(window.location.search);
        const identity = await this.client.getIdentity();
        this.dataStore.set('id', identity.email);
        const profile = await this.client.getProfile(identity.email);
        this.dataStore.set('profile', profile);
        if(profile == null) {
            document.getElementById("welcome").innerText = "Please log in or sign up to create Appointments."
        }
        document.getElementById("loading").innerText = "Loading...";
        document.getElementById("title").setAttribute('placeholder', 'Title');
        document.getElementById("description").setAttribute('placeholder', 'Description');
        this.setPlaceholders();

    }

    mount() {

        document.getElementById('allAppointments').addEventListener('click', this.redirectAllAppointments);
        document.getElementById('serviceMenu').addEventListener('click', this.redirectGetServiceMenu);
        document.getElementById('allClientProfiles').addEventListener('click', this.redirectAllClientProfiles);
        document.getElementById('createClientProfile').addEventListener('click', this.redirectCreateClientProfile);
        document.getElementById('logout').addEventListener('click', this.logout);
        document.getElementById('confirm').addEventListener('click', this.confirmRedirect);
        document.getElementById('submitted').addEventListener('click', this.submitFormData);



        this.client = new petproClient();
        this.clientLoaded();
    }


    async submitFormData(evt){
        evt.preventDefault();
        const title = document.getElementById('title').value || document.getElementById('title').getAttribute('placeholder');
        const description = document.getElementById('description').value ||  document.getElementById('description').getAttribute('placeholder');
        console.log(client, dateTime, pet, service);
        let profile;
        if(document.getElementById('welcome').innerText == "Please log in or sign up to create Services."){
            service = await this.client.createService(title, description, (error) => {
                errorMessageDisplay.innerText = `Error: ${error.message}`;
            });
        } else {
            service = await this.client.updateService(title, description, (error) => {
                errorMessageDisplay.innerText = `Error: ${error.message}`;
            });
        }


        this.dataStore.set('service', service);
        document.getElementById('title').innerText = title || service.serviceModel.title;
        document.getElementById('description').innerText = description || service.serviceModel.description;
        document.getElementById('loading-modal').remove();

    }
        confirmRedirect() {
            window.location.href = '/profile.html';
        }
        redirectUpdateClientProfile(){
            window.location.href = '/updateClientProfile.html';
        }
        redirectAllClientProfiles(){
            window.location.href = '/allClientProfiles.html';
        }
        redirectCreateAppointment(){
            window.location.href = '/createAppointment.html';
        }
        redirectAllAppointments(){
            window.location.href = '/allAppointments.html';
        }
        redirectServiceMenu(){
            window.location.href = '/serviceMenu.html';
        }
        logout(){
            this.client.logout();
        }
}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createService = new CreateService();
    createService.mount();
};

window.addEventListener('DOMContentLoaded', main);