import { apiInstance } from "./index.js";

const api = apiInstance();

function getCommonCodes(success, fail){
    api.get(`codes/category`).then(success).catch(fail);
}

export { getCommonCodes };