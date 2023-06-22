import petproClient from '../api/petproClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/header';
import DataStore from "../util/DataStore";

class CreateClientProfile extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount','confirmRedirect','submitFormData', 'redirectUpdateClientProfile','redirectAllClientProfiles',
        'redirectCreateAppointment','redirectAllAppointments', 'redirectGetServiceMenu', 'logout'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        const identity = await this.client.getIdentity();
        this.dataStore.set("email", identity.email);
        document.getElementById("name").setAttribute('placeholder', 'Name');
        document.getElementById("phone").setAttribute('placeholder', 'Phone');
        document.getElementById("address").setAttribute('placeholder', 'Address');
        document.getElementById("notes").setAttribute('placeholder', 'Notes');
        document.getElementById("pets").setAttribute('placeholder', 'Pets');

    }

    mount() {
  
        document.getElementById('AllAppointments').addEventListener('click', this.redirectAllAppointments);
        document.getElementById('ServiceMenu').addEventListener('click', this.redirectGetServiceMenu);
        document.getElementById('AllClientProfiles').addEventListener('click', this.redirectAllClientProfiles);
        document.getElementById('CreateAppointment').addEventListener('click', this.redirectCreateAppointment);
        document.getElementById('UpdateClientProfile').addEventListener('click', this.redirectUpdateClientProfile);
        document.getElementById('logout').addEventListener('click', this.logout);
        document.getElementById('submit').addEventListener('click', this.submitFormData);

      

        this.client = new petproClient();
        this.clientLoaded();
    }


    async submitFormData(evt){
        evt.preventDefault();
        const errorMessageDisplay = document.getElementById('error-message');
        const name = document.getElementById('name').value || document.getElementById('name').getAttribute('placeholder');
        const phone = document.getElementById('phone').value ||  document.getElementById('phone').getAttribute('placeholder');
        const address = document.getElementById('address').value ||  document.getElementById('address').getAttribute('placeholder');
        const notes = document.getElementById('notes').value ||  document.getElementById('notes').getAttribute('placeholder');
        const pets = document.getElementById('pets').value || document.getElementById('pets').getAttribute('placeholder');
        console.log(name, phone, address, notes, pets);
        let profile;
        if(document.getElementById('welcome').innerText == "Create Profile"){
            profile = await this.client.createClientProfile(name, phone, address, [notes], [pets], (error) => {
                errorMessageDisplay.innerText = `Error: ${error.message}`;
            });
        } else {
            profile = await this.client.updateClientProfile(this.dataStore.get('id'), name, phone, address, [notes], [pets], (error) => {
                errorMessageDisplay.innerText = `Error: ${error.message}`;
            });
        }


        this.dataStore.set('profile', profile);
        document.getElementById('name').innerText = name || profile.clientProfileModel.name;
        document.getElementById('phone').innerText = phone || profile.clientProfileModel.phone;
        document.getElementById('address').innerText = address || profile.clientProfileModel.address;
        document.getElementById('notes').innerText = notes || profile.clientProfileModel.notes;
        document.getElementById('pets').innerText = pets ||profile.clientProfileModel.pets;

    }
    confirmRedirect() {
        window.location.href = '/AllClientProfiles.html';
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
    redirectGetServiceMenu(){
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
    const createClientProfile = new CreateClientProfile();
    createClientProfile.mount();
};

window.addEventListener('DOMContentLoaded', main);