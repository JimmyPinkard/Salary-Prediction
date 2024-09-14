class Util{
    async request(endpoint:string, method:string, body:any) {
        return fetch(`https://127.0.0.1:8443${endpoint}`, {
            method: `${method}`,
            mode: "cors",
            headers: {
                "content-type": "application/json;charset=UTF-8"
            },
            body: body
        })
            .then(res => res.json())
            .catch(err => console.error(err));
    }
}
const util:Util = new Util();
export function request(endpoint:string, method:string, body:any) {
    return util.request(endpoint, method, body);
}

export default util;