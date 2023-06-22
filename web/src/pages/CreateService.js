import petproClient from '../api/petproClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/header';
import DataStore from "../util/DataStore";

class CreateService extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount','submitFormData', 'redirectAllClientProfiles',
        'redirectCreateAppointment','redirectAllAppointments', 'redirectCreateClientProfile', 'redirectGetServiceMenu', 'logout'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        const identity = await this.client.getIdentity();
        this.dataStore.set("email", identity.email);
        document.getElementById("title").setAttribute('placeholder', 'Title');
        document.getElementById("description").setAttribute('placeholder', 'Description');

    }

    mount() {

        document.getElementById('AllAppointments').addEventListener('click', this.redirectAllAppointments);
        document.getElementById('CreateAppointment').addEventListener('click', this.redirectCreateAppointment);
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
        const origButtonText = createButton.innerText;
        const title = document.getElementById('title').value || document.getElementById('title').getAttribute('placeholder');
        const description = document.getElementById('description').value ||  document.getElementById('description').getAttribute('placeholder');
        console.log(title, description);
         try {
             // Check that the user is authenticated
             const user = await this.client.getIdentity();
             if (!user) {
                  // If not authenticated, show error message
                  throw new Error('Only authenticated users can create a service.');
             }
             const serviceCreator = user.email;
             const event = await this.client.createService(title, description,
                   (error) => {
                   });
                   console.log(title, description);
                window.location.href ='/ServiceMenu.html';
                } catch (error) {
            }
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
    const createService = new CreateService();
    createService.mount();
};

window.addEventListener('DOMContentLoaded', main);