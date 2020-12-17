exports.handler = async (event) => {
    var json = {
        "codigo": event.codigo,
        "versao":"1.4.3",
        "conteudo": "Este texto representa o conteúdo do documento"
    };
    const response = {
        statusCode: 200,
        body: JSON.stringify(json),
    };
    return response;
};
