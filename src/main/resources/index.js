const fs = require('fs/promises');
const path = require('path');
const mjml = require('mjml');

const source = process.env.MJML_SOURCE || 'src/main/resources/mjml';
const destination = process.env.MJML_DEST || 'target/classes/templates';
const extension = process.env.MJML_EXT || 'ftlh';

async function destinationExists() {
    try {
        await fs.access(path.resolve(destination));
    } catch (err) {
        await fs.mkdir(path.resolve(destination), { recursive: true });
    }
}

async function convertMjml() {
    const files = await fs.readdir(path.resolve(source), { encoding: 'utf-8' });
    for (const file of files) {
        const template = await fs.readFile(path.resolve(`${source}/${file}`), {
            encoding: 'utf-8',
        });
        const { html } = mjml(template);
        const filename = file.replace('.mjml', `.${extension}`);
        await fs.writeFile(path.resolve(`${destination}/${filename}`), html, { encoding: 'utf-8' });
    }
}

(async function() {
    await destinationExists();
    await convertMjml();
    process.exit(0);
})()
