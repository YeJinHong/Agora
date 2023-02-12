import { apiInstance } from "./index.js";

const api = apiInstance();

async function searchAll(condition, success, fail) {
    await api.get(`/debates/`, condition).then(success).catch(fail);
}

export { searchAll };
