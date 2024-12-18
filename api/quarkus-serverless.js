const { spawn } = require('child_process');

module.exports = (req, res) => {
    const quarkusApp = spawn('java', ['-jar', './target/quarkus-app/quarkus-run.jar']);

    quarkusApp.stdout.on('data', (data) => {
        console.log(`stdout: ${data}`);
    });

    quarkusApp.stderr.on('data', (data) => {
        console.error(`stderr: ${data}`);
    });

    quarkusApp.on('close', (code) => {
        console.log(`child process exited with code ${code}`);
    });


    res.status(200).send('Quarkus app is running!');
};
