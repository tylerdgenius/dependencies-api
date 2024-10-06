module.exports = {
  apps: [
    {
      name: "vuga-api",
      script: "docker",
      args: "run --name ${process.env.CONTAINER_NAME} -p 9000:9000 tylerdev24/metrobuzz-deps:latest",
      watch: false,
      autorestart: true,
      max_restarts: 10,
      restart_delay: 5000,
    },
  ],
};
