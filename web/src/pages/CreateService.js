import petproClient from '../api/petproClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/header';
import DataStore from "../util/DataStore";

class CreateService extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount','confirmRedirect','submitFormData', 'redirectUpdateClientProfile','redirectAllClientProfiles',
        'redirectCreateAppointment','redirectAllAppointments', 'redirectCreateClientProfile', 'redirectServiceMenu', 'setPlaceholders', 'logout'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        // const urlParams = new URLSearchParams(window.location.search);
        const identity = await this.client.getIdentity();
        this.dataStore.set('id', identity.email);
        const service = await this.client.createService(service);
        this.dataStore.set('service', service);
        if(service == null) {
            document.getElementById("welcome").innerText = "Please log in or sign up to create Services."
        }
        document.getElementById("title").setAttribute('placeholder', 'Title');
        document.getElementById("description").setAttribute('placeholder', 'Description');
        this.setPlaceholders();

    }
    async setPlaceholders(){
        const service = this.dataStore.get("service");
        console.log("this one",service)
        if (service == null) {
            return;
        }
        if (service.serviceModel.title && service.serviceModel.description) {
            document.getElementById("title").innerText =  service.serviceModel.title
            document.getElementById('description').setAttribute('placeholder', service.serviceModel.description);
        }
        document.getElementById("loading").remove();
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
            window.location.href = '/ServiceMenu.html';
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
        redirectCreateClientProfile(){
            window.location.href = '/CreateClientProfile.html';
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
    const createService = new CreateService();
    createService.mount();
};

window.addEventListener('DOMContentLoaded', main);