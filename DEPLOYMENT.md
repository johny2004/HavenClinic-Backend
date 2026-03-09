# 🚀 Guía de Deployment - HavenClinic

## 📋 Resumen
Esta guía te ayudará a desplegar tu backend en la nube y conectarlo con tu frontend en GitHub Pages.

---

## ✅ Cambios Realizados

### Backend
1. ✅ Configuración CORS actualizada para permitir GitHub Pages
2. ✅ Perfil de producción creado en `application.properties`
3. ✅ Archivos de deployment creados (Procfile, render.yaml, system.properties)

### Frontend
1. ✅ Archivos de environment creados (`environment.ts` y `environment.prod.ts`)
2. ✅ Todos los servicios actualizados para usar variables de environment
3. ✅ `angular.json` configurado para usar el environment correcto

---

## 🎯 Pasos para el Deployment

### PASO 1: Desplegar el Backend

#### Opción A: Render (Recomendado - Gratis)

1. **Crear cuenta en Render**
   - Ve a https://render.com y crea una cuenta gratuita
   - Conecta tu cuenta de GitHub

2. **Crear un nuevo Web Service**
   - Click en "New +" → "Web Service"
   - Conecta tu repositorio del backend (HavenClinic-Backend)
   - Render detectará automáticamente el `render.yaml`

3. **Configurar el servicio**
   - **Name:** havenclinic-backend
   - **Environment:** Java
   - **Build Command:** `./mvnw clean install -DskipTests`
   - **Start Command:** `java -Dserver.port=$PORT -Dspring.profiles.active=prod -jar target/clinicahaven-0.0.1-SNAPSHOT.jar`
   - **Plan:** Free
   
4. **Variables de entorno**
   Render las configurará automáticamente desde render.yaml:
   - `SPRING_PROFILES_ACTIVE=prod`
   - `JAVA_OPTS=-Xmx512m`

5. **Deploy**
   - Click en "Create Web Service"
   - El deploy tomará 5-10 minutos
   - Una vez completado, obtendrás una URL como: `https://havenclinic-backend.onrender.com`

#### Opción B: Railway (Alternativa - Gratis con límites)

1. Ve a https://railway.app
2. Conecta tu GitHub
3. "New Project" → "Deploy from GitHub repo"
4. Selecciona HavenClinic-Backend
5. Railway detectará que es Java automáticamente
6. Agrega variables de entorno:
   - `SPRING_PROFILES_ACTIVE=prod`
7. Deploy automático

#### Opción C: Heroku (Requiere tarjeta de crédito)

1. Instala Heroku CLI
2. En la carpeta del backend:
```bash
heroku login
heroku create havenclinic-backend
heroku config:set SPRING_PROFILES_ACTIVE=prod
git push heroku main
```

---

### PASO 2: Actualizar el Frontend con la URL del Backend

1. **Edita el archivo de producción del frontend:**
   
   Archivo: `HavenClinic-Angular-Frontend/src/environments/environment.prod.ts`
   
   ```typescript
   export const environment = {
     production: true,
     apiUrl: 'https://TU-URL-DEL-BACKEND.onrender.com'  // ⬅️ CAMBIAR ESTO
   };
   ```
   
   **Reemplaza con tu URL real** (la que te dio Render/Railway/Heroku)
   
   ⚠️ **IMPORTANTE:** NO incluyas la barra final (/).

2. **Actualiza también el CORS en el backend:**
   
   Archivo: `HavenClinic-Backend/src/main/java/puj/web/clinicahaven/security/CorsConfig.java`
   
   Reemplaza:
   ```java
   config.addAllowedOrigin("https://[TU-USUARIO].github.io");
   ```
   
   Con tu URL real de GitHub Pages, por ejemplo:
   ```java
   config.addAllowedOrigin("https://quinteroep.github.io");
   ```

3. **Commit y push los cambios del backend:**
   ```bash
   cd HavenClinic-Backend
   git add .
   git commit -m "Update CORS for production"
   git push
   ```
   
   Render/Railway harán el redeploy automáticamente.

---

### PASO 3: Desplegar el Frontend en GitHub Pages

1. **Asegúrate de que el repositorio del frontend esté en GitHub**

2. **Actualiza el package.json si es necesario:**
   
   Verifica que en `package.json` tengas:
   ```json
   "scripts": {
     "build:prod": "ng build --configuration production --base-href /HavenClinic-Angular-Frontend/",
     "deploy": "gh-pages -d dist/haven-angular",
     "build:deploy": "npm run build:prod && npm run deploy"
   }
   ```
   
   Nota: Reemplaza `/HavenClinic-Angular-Frontend/` con el nombre de tu repositorio.

3. **Instala las dependencias (si no lo has hecho):**
   ```bash
   cd HavenClinic-Angular-Frontend
   npm install
   ```

4. **Construir y desplegar:**
   ```bash
   npm run build:deploy
   ```
   
   Esto:
   - Construirá tu aplicación en modo producción
   - Subirá los archivos a la rama `gh-pages`

5. **Habilitar GitHub Pages:**
   - Ve a tu repositorio en GitHub
   - Settings → Pages
   - Source: Selecciona la rama `gh-pages` y carpeta `/ (root)`
   - Guarda
   - Tu sitio estará disponible en: `https://[tu-usuario].github.io/HavenClinic-Angular-Frontend/`

---

## 🔍 Verificación

### Backend
1. Visita tu URL del backend: `https://tu-backend.onrender.com/swagger-ui.html`
2. Deberías ver la documentación de Swagger
3. Prueba un endpoint público como `/login`

### Frontend
1. Visita tu URL de GitHub Pages
2. Abre DevTools (F12) → Network
3. Intenta hacer login
4. Verifica que las peticiones vayan a tu backend en producción

### CORS
Si ves errores de CORS:
1. Verifica que la URL en `CorsConfig.java` coincida con tu GitHub Pages URL
2. Asegúrate de que NO tiene barra final
3. Redeploy el backend si hiciste cambios

---

## 📝 Checklist Final

- [ ] Backend desplegado en Render/Railway/Heroku
- [ ] URL del backend obtenida y anotada
- [ ] `environment.prod.ts` actualizado con URL del backend
- [ ] `CorsConfig.java` actualizado con URL de GitHub Pages
- [ ] Cambios del backend pusheados y redesployados
- [ ] Frontend built y deployado a GitHub Pages
- [ ] GitHub Pages habilitado en configuración del repositorio
- [ ] Pruebas de login/funcionalidad realizadas

---

## 🐛 Troubleshooting

### Error: "CORS policy: No 'Access-Control-Allow-Origin'"
- Verifica `CorsConfig.java` tenga la URL correcta de GitHub Pages
- Asegúrate que NO tenga barra final
- Redeploy el backend

### Error: "Failed to fetch" o "Network Error"
- Verifica que `environment.prod.ts` tenga la URL correcta del backend
- Verifica que el backend esté corriendo (visita su Swagger UI)
- Verifica que el backend acepte HTTPS (Render/Railway lo hacen automáticamente)

### Backend en Render tarda mucho o se "duerme"
- Los planes gratuitos de Render "duermen" después de 15 min de inactividad
- La primera petición puede tardar 30-60 segundos en "despertar"
- Considera usar un servicio de "ping" gratuito para mantenerlo activo

### Errores al hacer npm run build:deploy
- Verifica que `gh-pages` esté instalado: `npm install gh-pages --save-dev`
- Verifica que el base-href en package.json coincida con el nombre de tu repo

---

## 📞 URLs Importantes

- **Backend API:** https://tu-backend.onrender.com
- **Backend Swagger:** https://tu-backend.onrender.com/swagger-ui.html
- **Frontend:** https://[tu-usuario].github.io/HavenClinic-Angular-Frontend/
- **Render Dashboard:** https://dashboard.render.com
- **Railway Dashboard:** https://railway.app/dashboard

---

## 🔄 Actualizaciones Futuras

Para actualizar tu aplicación en el futuro:

### Backend
1. Haz tus cambios en el código
2. Commit y push a GitHub
3. Render/Railway redesployarán automáticamente

### Frontend
1. Haz tus cambios en el código
2. Ejecuta: `npm run build:deploy`
3. Los cambios aparecerán en GitHub Pages en 1-2 minutos

---

## 💡 Notas Adicionales

- **Base de datos:** Actualmente usas H2 en modo archivo. En producción se creará `proddb` en el servidor.
- **Persistencia:** Los datos persistirán entre deployments, pero podrían perderse si cambias de plan o servidor.
- **Seguridad:** Considera usar PostgreSQL en producción para mayor robustez.
- **JWT:** Los tokens tienen un tiempo de expiración. Considera implementar refresh tokens.
- **Logs:** Usa los dashboards de Render/Railway para ver logs y debugging.

---

¡Buena suerte con tu deployment! 🚀
