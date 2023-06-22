import petproClient from '../api/petproClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/header';
import DataStore from "../util/DataStore";

class ViewClientProfile extends BindingClass {
    constructor() {+
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'getClientProfile', 'redirectUpdateClientProfile','redirectAllAppointments',
        'redirectCreateAppointment','redirectAllClientProfiles','logout','redirectServiceMenu'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);

    }

    /**
     * Once the client is loaded, get the profile metadata.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get('id');
        const identity = await this.client.getIdentity();
        this.dataStore.set('id', identity.email);
        const profile = await this.client.getClientProfile(id);
        this.dataStore.set('profile', profile);

        this.dataStore.set('name', profile.clientProfileModel.name);
        this.dataStore.set('phone', profile.clientProfileModel.phone);
        this.dataStore.set('address', profile.clientProfileModel.address);
        this.dataStore.set('notes', profile.clientProfileModel.notes);
        this.dataStore.set('pets', profile.clientProfileModel.pets);

    }
    /**
     * Add the header to the page and load the petproClient.
     */
    mount() {

    document.getElementById('AllAppointments').addEventListener('click', this.redirectAllAppointments);
    document.getElementById('ServiceMenu').addEventListener('click', this.redirectGetServiceMenu);
    document.getElementById('AllClientProfiles').addEventListener('click', this.redirectAllClientProfiles);
    document.getElementById('CreateClientProfile').addEventListener('click', this.redirectCreateClientProfile);
    document.getElementById('UpdateClientProfile').addEventListener('click', this.redirectUpdateClientProfile);
    document.getElementById('logout').addEventListener('click', this.logout);

    this.client = new petproClient();
    this.clientLoaded();
    }

    async getClientProfile(id) {
        document.getElementById('name').textContext = profile.name;
        document.getElementById('phone').textContext = profile.phone;
        document.getElementById('address').textContext = profile.address;
        document.getElementById('notes').textContext = profile.notes;
        document.getElementById('pets').textContext = profile.pets;
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
    const viewClientProfile = new ViewClientProfile();
    viewClientProfile.mount();
};

window.addEventListener('DOMContentLoaded', main);
