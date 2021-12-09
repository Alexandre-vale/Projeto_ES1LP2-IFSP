// fetch('https://reqres.in/api/users')
//     .then(res => 
//         {
//             if (res.ok)
//             {
//                 console.log('Sucesso')
//             }
//             else
//             {
//                 console.log('Erro')
//             }
//         })
//     .then(data => console.log(data))

// fetch('https://reqres.in/api/users',
// {
//     method: 'POST',
//     headers:
//     {
//         'Content-Type': 'application/json'
//     },
//     body:JSON.stringify(
//     {
//         name: 'User 1'
//     })
// })
//     .then(res => 
//         {
//             return res.json()
//         })
//     .then(data => console.log(data))

const feedDisplay = document.querySelector('#feed')

fetch('https://reqres.in/api/users',
    {
        method: 'POST',
        headers:
        {
            var: myDic = {
                id: 44,
                nome: "Cu"
            },
            'Content-Type': 'application/json'
        },
        // body: 


        // myDic = JSON.stringify(
        //     {
        //         id: '555',
        //         nome: 'Pato'
        //     },
        //     {
        //         id: '888',
        //         nome: 'Andre'
        //     })
    })

    .then(res => { return res.json() })

    .then(myDic => {
        // data.forEach(nome => {
        //     const mestre = '<h3>' + nome.mestre + '</h3>'
        //     feedDisplay.insertAdjacentHTML("beforeend", title)
        // });
        console.log(myDic)
    })
    
    // .then(response => { return response.json() })
    // .then(data => {
    //     data.forEach(itens => 
    //     {
    //         const nome = itens.first_name
    //         feedDisplay.insertAdjacentHTML("beforeend", first_name)
    //     })
    // })

    // .catch(err => console.log(err))