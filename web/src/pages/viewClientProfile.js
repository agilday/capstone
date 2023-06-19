import petproClient from '../api/petproClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/dannaHeader';
import DataStore from "../util/DataStore";

class ViewProfile extends BindingClass {
    constructor() {+
        super();
        this.bindClassMethods(['clientLoaded', 'mount','thisPageRemoveFrom','redirectUpdateClientProfile','redirectAllAppointments',
        'redirectCreateAppointment','redirectAllClientProfiles','logout','Service Menu'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        // console.log("viewprofile constructor");
    }

    /**
     * Once the client is loaded, get the profile metadata.
     */
    async clientLoaded() {
        // const urlParams = new URLSearchParams(window.location.search);
        const identity = await this.client.getIdentity();
        const profile = await this.client.getProfile(identity.email);
        this.dataStore.set('profile', profile);

        this.dataStore.set('name', profile.clientProfileModel.name);
        this.dataStore.set('phone', profile.clientProfileModel.phone);
        this.dataStore.set('address', profile.clientProfileModel.address);
        this.dataStore.set('notes', profile.clientProfileModel.notes);
        this.dataStore.set('pets', profile.clientProfileModel.pets);

    }
    /**
     * Add the header to the page and load the dannaClient.
     */
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


    async thisPageRemoveFrom(result){
        this.client.removeEventFromProfile(result);
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
    const viewClientProfile = new viewClientProfile();
    viewClientProfile.mount();
};

window.addEventListener('DOMContentLoaded', main);
