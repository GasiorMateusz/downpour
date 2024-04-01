import { library } from "@fortawesome/fontawesome-svg-core";
import {
    faHome,
    faSignInAlt,
    faSignOutAlt,
    faUser,
    faUserPlus,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);

export { FontAwesomeIcon };
