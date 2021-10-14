# GuessNumber

Aplicación que consta de 3 Activitys donde se genera un numero aleatorio y trata de adivinarlo. Estas tres Activitys son: 

 - ConfigActivity
 - PlayActivity
 - EndPlayActivity

# ConfigActivity

Primera actividad en la que se le pide al usuario mediante dos EditText un nombre y un número de intentos. Tambien consta de un botón para empezar a jugar y pasar a la actividad PlayActivity.
```java
    if (!TextUtils.isEmpty(edNombre.getText()) && !TextUtils.isEmpty(edIntentos.getText())) {  
    if (TextUtils.isDigitsOnly(edIntentos.getText())) {  
        Bundle bundle = new Bundle();  
  

    Message message = new Message();  
      message.setNombre(binding.edNombre.getText().toString());  
      message.setIntentos(Integer.parseInt(binding.edIntentos.getText().toString()));  
      bundle.putSerializable("message", message);  
      
      Intent intent = new Intent(this, PlayActivity.class);  
      intent.putExtras(bundle);  
      startActivity(intent);  
      } else {  
            showMessage(R.string.comprTipo);  
      }  
    } else {  
        showMessage(R.string.campoVacio);
        }
    }
    private void showMessage(int mensaje) {  
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();  
        }
```
Este fragmento de código es el método del evento del botón jugar. Lo primero que se hace es comprobar que ninguno de los campos estén vacios y que el campo del numero de intentos sea un numero entero positivo.
En caso de que esto no se cumpla se mostrará un mensaje flotante avisando de que un error se ha producido.
Además tambien manda la informacion recogida a la siguiente activity.

<a href="https://gyazo.com/758656e5e7799880c8c108e9176beeaf">Captura</a>

# PlayActivity
En esta Activity se pide un numero para intentar adivinar el numero aleatorio generado y dependiendo si el numero aleatorio es mayor o menor que el introducido en el EditText se cambia la visibilidad a uno de los dos TextView. Tiene tambien dos botones, uno para comprobar el numero y otro para borrar el campo

```java
public void Jugando() {  
  
    if (!TextUtils.isEmpty(binding.edIntentado.getText())) {  
        if (TextUtils.isDigitsOnly(binding.edIntentado.getText())) {  
            int numero = Integer.parseInt(binding.edIntentado.getText().toString());  
 if (numero == alea) {  
                isGanado = true;  
  ultimaActividad();  
  } else {  
                intentostotales--;  
 if (intentostotales == 0) {  
                    ultimaActividad();  
 return;  }  
                if (numero < alea) {  
                    binding.tvMayor.setVisibility(View.VISIBLE);  
  } else {  
                    binding.tvMenor.setVisibility(View.VISIBLE);  
  }  
            }  
        } else {  
            Toast.makeText(this, R.string.comprTipo, Toast.LENGTH_LONG);  
  }  
    } else {  
        Toast.makeText(this, R.string.campoVacio, Toast.LENGTH_LONG);  
  }  
}
```
Este es el metodo del evento del boton jugar. Como se puede comprobar hace todo el algoritmo de decir si el numero es mayor o menor que el generado y comprueba que el EditText no esté ni vacio y tenga que ser un numero. En caso de que alguna de las condiciones no se cumplan salta un mensaje de error. 

```java
public void ultimaActividad() {  
    Bundle bundle = new Bundle();  
  bundle.putBoolean("ganado", isGanado);  
  bundle.putInt("intentos", (intentosConsumidos-intentostotales) + 1);  
  bundle.putInt("alea", alea);  
  Intent intent = new Intent(this, EndPlayActivity.class);  
  intent.putExtras(bundle);  
  startActivity(intent);  
}
```
Manda los datos de esta activity a la ultima y pasa un valor booleano para comprobar si ha acertado o no.
```java
private void Borrar() {  
  
    binding.edIntentado.setText("");  
  binding.tvMayor.setVisibility(View.INVISIBLE);  
  binding.tvMenor.setVisibility(View.INVISIBLE);  
}
```
Metodo del evento click del boton borrar. Simplemente vacia el campo e invisibiliza los TextView

<a href="https://gyazo.com/37e9958c3e4e739bd5bc1f86c89ca0c5">Captura</a>

# EndPlayActivity

Esta es la ultima clase y recoge el bundle de la anterior con el valor true o false dependiendo si el numero ha sido acertado o no.
```java
protected void onCreate(Bundle savedInstanceState) {  
    super.onCreate(savedInstanceState);  
  binding = ActivityEndPlayBinding.inflate(getLayoutInflater());  
  setContentView(binding.getRoot());  
 boolean hasGanado = false;  
  
  Intent intent = getIntent();  
  Bundle bundle = intent.getExtras();  
 if (hasGanado != bundle.getBoolean("ganado")) {  
        binding.tvGanado.setVisibility(View.VISIBLE);  
  binding.tvGanado.setText(getString(R.string.hasGanadoEn) + " " + bundle.getInt("intentos") + " " + getString(R.string.intentos));  
  } else {  
        binding.tvPerdiste.setVisibility(View.VISIBLE);  
  binding.tvPerdiste.setText(getString(R.string.PerdidoFinal) + " " + bundle.getInt("alea"));  
  }  
  
}
```
Simplemente recoge el bundle de la anterior clase y dependiendo del valor del booleano que contiene cambia la visibilidad de un texto o de otro. 

<a href="https://gyazo.com/2fe097ce08b4762491ac6913eccac0c1">Captura</a>

# Clase Objeto


## Message

Es la clase **Serializable** que contiene el objeto con el nombre y el numero de intentos introducidos. Se usa para ir pasandose de una clase a otra
```java
import java.io.Serializable;  
  
public class Message implements Serializable {  
    String nombre;  
 int intentos;  
  
 public Message(){}  
  
    public Message(String nombre, int intentos) {  
        this.nombre = nombre;  
 this.intentos = intentos;  
  }  
  
    public String getNombre() {  
        return nombre;  
  }  
  
    public void setNombre(String nombre) {  
        this.nombre = nombre;  
  }  
  
    public int getIntentos() {  
        return intentos;  
  }  
  
    public void setIntentos(int intentos) {  
        this.intentos = intentos;  
  }  
  
    @Override  
  public String toString() {  
        return "Message{" +  
                "nombre='" + nombre + '\'' +  
                ", intentos=" + intentos +  
                '}';  
  }  
}
```



# Layouts

## ConfigActivity
```xml
<TextView  
  android:id="@+id/textView"  
  android:layout_width="wrap_content"  
  android:layout_height="wrap_content"  
  android:layout_marginStart="32dp"  
  android:layout_marginTop="116dp"  
  android:text="@string/tvNombre"  
  app:layout_constraintStart_toStartOf="parent"  
  app:layout_constraintTop_toTopOf="parent" />  
  
<TextView  
  android:id="@+id/textView2"  
  android:layout_width="wrap_content"  
  android:layout_height="wrap_content"  
  android:layout_marginStart="32dp"  
  android:layout_marginTop="200dp"  
  android:text="@string/tvIntentos"  
  app:layout_constraintStart_toStartOf="parent"  
  app:layout_constraintTop_toTopOf="parent" />  
  
<EditText  
  android:id="@+id/edNombre"  
  android:layout_width="wrap_content"  
  android:layout_height="wrap_content"  
  android:ems="10"  
  android:inputType="textPersonName"  
  android:minHeight="48dp"  
  app:layout_constraintBaseline_toBaselineOf="@+id/textView"  
  app:layout_constraintEnd_toEndOf="parent"  
  app:layout_constraintHorizontal_bias="0.243"  
  app:layout_constraintStart_toEndOf="@+id/textView"  
  tools:ignore="SpeakableTextPresentCheck" />  
  
<EditText  
  android:id="@+id/edIntentos"  
  android:layout_width="wrap_content"  
  android:layout_height="wrap_content"  
  android:ems="10"  
  android:inputType="textPersonName"  
  android:minHeight="48dp"  
  app:layout_constraintBaseline_toBaselineOf="@+id/textView2"  
  app:layout_constraintEnd_toEndOf="parent"  
  app:layout_constraintHorizontal_bias="0.243"  
  app:layout_constraintStart_toEndOf="@+id/textView2"  
  tools:ignore="SpeakableTextPresentCheck" />  
  
<Button  
  android:id="@+id/btnJugar"  
  android:layout_width="wrap_content"  
  android:layout_height="wrap_content"  
  android:layout_marginStart="148dp"  
  android:layout_marginTop="368dp"  
  android:text="@string/btnJugar"  
  android:onClick="getOnClick"  
  app:layout_constraintStart_toStartOf="parent"  
  app:layout_constraintTop_toTopOf="parent" />
```
## PlayActivity

```xml
<TextView  
  android:id="@+id/tvNumero"  
  android:layout_width="wrap_content"  
  android:layout_height="wrap_content"  
  android:layout_marginStart="56dp"  
  android:layout_marginTop="104dp"  
  android:text="@string/tvIntento"  
  app:layout_constraintStart_toStartOf="parent"  
  app:layout_constraintTop_toTopOf="parent" />  
  
<EditText  
  android:id="@+id/edIntentado"  
  android:layout_width="0dp"  
  android:layout_height="wrap_content"  
  android:layout_marginStart="16dp"  
  android:layout_marginEnd="16dp"  
  android:ems="10"  
  android:hint="@string/intNumero"  
  android:inputType="textPersonName"  
  android:minHeight="48dp"  
  app:layout_constraintBaseline_toBaselineOf="@+id/tvNumero"  
  app:layout_constraintEnd_toEndOf="parent"  
  app:layout_constraintStart_toEndOf="@+id/tvNumero" />  
  
<TextView  
  android:id="@+id/tvMenor"  
  android:visibility="invisible"  
  android:layout_width="wrap_content"  
  android:layout_height="wrap_content"  
  android:layout_marginStart="64dp"  
  android:layout_marginTop="224dp"  
  android:text="@string/tvMenor"  
  app:layout_constraintStart_toStartOf="parent"  
  app:layout_constraintTop_toTopOf="parent" />  
  
<TextView  
  android:id="@+id/tvMayor"  
  android:visibility="invisible"  
  android:layout_width="wrap_content"  
  android:layout_height="wrap_content"  
  android:layout_marginStart="164dp"  
  android:layout_marginTop="224dp"  
  android:text="@string/tvMayor"  
  app:layout_constraintStart_toEndOf="@+id/tvMenor"  
  app:layout_constraintTop_toTopOf="parent" />  
  
<Button  
  android:id="@+id/btnIntento"  
  android:layout_width="wrap_content"  
  android:layout_height="wrap_content"  
  android:layout_marginEnd="120dp"  
  android:onClick="GetOnClick"  
  android:layout_marginBottom="300dp"  
  android:text="@string/btnIntento"  
  app:layout_constraintBottom_toBottomOf="parent"  
  app:layout_constraintEnd_toStartOf="@+id/btnBorrar" />  
  
<Button  
  android:id="@+id/btnBorrar"  
  android:layout_width="wrap_content"  
  android:layout_height="wrap_content"  
  android:onClick="GetOnClick"  
  android:layout_marginStart="260dp"  
  android:layout_marginBottom="300dp"  
  android:text="@string/btnBorrar"  
  app:layout_constraintBottom_toBottomOf="parent"  
  app:layout_constraintStart_toStartOf="parent" />
```

## EndPlayActivity

```xml
<TextView  
  android:id="@+id/tvGanado"  
  android:layout_width="wrap_content"  
  android:layout_height="wrap_content"  
  android:layout_marginTop="132dp"  
  android:text="@string/tvHasGanado"  
  android:visibility="invisible"  
  app:layout_constraintEnd_toEndOf="parent"  
  app:layout_constraintHorizontal_bias="0.498"  
  app:layout_constraintStart_toStartOf="parent"  
  app:layout_constraintTop_toTopOf="parent" />  
  
<TextView  
  android:id="@+id/tvPerdiste"  
  android:layout_width="wrap_content"  
  android:layout_height="wrap_content"  
  android:layout_marginTop="252dp"  
  android:text="@string/tvHasPerdido"  
  android:visibility="invisible"  
  app:layout_constraintEnd_toEndOf="parent"  
  app:layout_constraintStart_toStartOf="parent"  
  app:layout_constraintTop_toTopOf="parent" />
```

# Strings.xml

Aqui se han puesto todos los objetos String para luego poder abrirlos con el editor de idiomas y dependiendo del idioma del movil se muestre en español o ingles.
 ```xml
 <resources>  
 <string name="app_name">GuessNumber</string>  
 <string name="tvNombre">"Nombre: "</string>  
 <string name="tvIntentos">"Intentos: "</string>  
 <string name="btnJugar">"A JUGAR! "</string>  
 <string name="tvIntento">"Intento: "</string>  
 <string name="tvMenor">MENOR</string>  
 <string name="tvMayor">MAYOR</string>  
 <string name="btnBorrar">Borrar</string>  
 <string name="btnIntento">Intentar</string>  
 <string name="intNumero">Prueba con un numero</string>  
 <string name="GameOver">Perdiste</string>  
 <string name="Ganaste">Has acertado!</string>  
 <string name="comprTipo">El campo intentos debe ser un numero</string>  
 <string name="campoVacio">Los campos no pueden estar vacios</string>  
 <string name="tvHasGanado">GANASTE</string>  
 <string name="tvHasPerdido">PERDISTE</string>  
 <string name="PerdidoFinal">"Has perdido. El numero correcto era "</string>  
 <string name="hasGanadoEn">"Has ganado en "</string>  
 <string name="intentos">intentos</string>  
</resources>
 ```

