import dannaClient from '../api/dannaClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/dannaHeader';
import DataStore from "../util/DataStore";

class CreateProfile extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount','confirmRedirect','submitFormData', 'redirectAllClientProfiles',
        'redirectCreateAppointment','redirectAllAppointments', 'redirectServiceMenu', 'logout','setPlaceholders'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        // console.log("viewprofile constructor");
    }

    async clientLoaded() {
        // const urlParams = new URLSearchParams(window.location.search);
        const identity = await this.client.getIdentity();
        this.dataStore.set('id', identity.email);
        const profile = await this.client.getClientProfile(identity.email);
        this.dataStore.set('profile', profile);
        if(profile == null) {
            document.getElementById("welcome").innerText = "Welcome! First Lets Make Your Profile!"
        }
        document.getElementById("loading").innerText = "Loading.....";
        document.getElementById("name").setAttribute('placeholder', 'Name');
        document.getElementById("phone").setAttribute('placeholder', 'Phone');
        document.getElementById("address").setAttribute('placeholder', 'Address');
        document.getElementById("notes").setAttribute('placeholder', 'Notes');
        document.getElementById("pets").setAttribute('placeholder', 'Pets');
        this.setPlaceholders();

    }

    mount() {
  
        document.getElementById('profilePic').addEventListener('click', this.redirectUpdateClientProfile);
        document.getElementById('allAppointments').addEventListener('click', this.redirectGetAllAppointments);
        document.getElementById('createAppointment').addEventListener('click', this.redirectCreateAppointment);
        document.getElementById('allClientProfiles').addEventListener('click', this.redirectGetAllClientProfiles);
        document.getElementById('logout').addEventListener('click', this.logout);
        document.getElementById('door').addEventListener('click', this.logout);
        document.getElementById('confirm').addEventListener('click', this.confirmRedirect);
        document.getElementById('submited').addEventListener('click', this.submitFormData);

      

        this.client = new petProClient();
        this.clientLoaded();
    }

    async setPlaceholders(){
        const profile = this.dataStore.get("profile");
        console.log("this one",profile)
        if (profile == null) {
            return;
        }
        if (profile.clientProfileModel.name) {
            document.getElementById("name").innerText =  profile.clientProfileModel.name
            document.getElementById('phone').setAttribute('placeholder', profile.clientProfileModel.phone);
            document.getElementById('address').setAttribute('placeholder', profile.clientProfileModel.address);
        }
        if (profile.clientProfileModel.notes) {
            document.getElementById('notes').setAttribute('placeholder',profile.clientProfileModel.notes);
        }
        if (profile.clientProfileModel.pets) {
            document.getElementById('location').setAttribute('placeholder',profile.clientProfileModel.pets);
        }
        document.getElementById("loading").remove();
    }


    async submitFormData(evt){
        evt.preventDefault();
        const name = document.getElementById('name').value || document.getElementById('name').getAttribute('placeholder');
        const phone = document.getElementById('phone').value ||  document.getElementById('phone').getAttribute('placeholder');
        const address = document.getElementById('address').value ||  document.getElementById('address').getAttribute('placeholder');
        const notes = document.getElementById('notes').value ||  document.getElementById('notes').getAttribute('placeholder');
        const pets = document.getElementById('pets').value || document.getElementById('pets').getAttribute('placeholder');
        console.log(name, phone, address, notes, pets);
        let profile;
        if(document.getElementById('welcome').innerText == "Create a new Client Profile"){
            profile = await this.client.createClientProfile(name, phone, address, notes, pets, (error) => {
                errorMessageDisplay.innerText = `Error: ${error.message}`;
            });
        } else {
            profile = await this.client.updateProfile(this.dataStore.get('id'), name, phone, address, notes, pets, (error) => {
                errorMessageDisplay.innerText = `Error: ${error.message}`;
            });
        }
        

        this.dataStore.set('profile', profile);
        document.getElementById('name').innerText = name || profile.clientProfileModel.name;
        document.getElementById('phone').innerText = phone || profile.clientProfileModel.phone;
        document.getElementById('address').innerText = address || profile.clientProfileModel.address;
        document.getElementById('notes').innerText = notes || profile.clientProfileModel.notes;
        document.getElementById('pets').innerText = pets ||profile.clientProfileModel.pets;
        document.getElementById('loading-modal').remove();
        
    }
    confirmRedirect() {
        window.location.href = '/clientProfile.html';
    }
    redirectServiceMenu(){
        window.location.href = '/serviceMenu.html';
    }
    redirectAllAppointments(){
        window.location.href = '/allAppointments.html';
    }
    redirectCreateAppointment(){
        window.location.href = '/createAppointment.html';
    }
    redirectAllClientProfiles(){
        window.location.href = '/allClientProfiles.html';
    }
    logout(){
        this.client.logout();
    }
}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createProfile = new CreateProfile();
    createProfile.mount();
};

window.addEventListener('DOMContentLoaded', main);