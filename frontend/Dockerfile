FROM node:16.19.0
# ENV NODE_ENV=production
WORKDIR /usr/src/app
COPY ["package.json", "package-lock.json*", "./"]
RUN npm install --legacy-peer-deps
COPY . .
EXPOSE 3000
# RUN chown -R node /usr/src/app
# USER node
CMD ["npm", "run", "serve"]