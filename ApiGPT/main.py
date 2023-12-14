from flask import Flask, request
from openai import OpenAI

client = OpenAI(api_key = "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")
app = Flask(__name__)


@app.route('/gptAtaque', methods=['GET'])
def obtener_respuesta_gpt():
    tipoataque = request.args.get('tipoAtaque')

    response = client.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "system", "content": "Actua como experto en ciberseguridad con respuestas cortas y consisas"},
            {"role": "user", "content": "Dame un conjunto de medidas para atender un ataque de " + tipoataque}
        ]
    )

    mensaje = response.choices[0].message.content
    return mensaje


if __name__ == '__main__':
    app.run(debug=True)
