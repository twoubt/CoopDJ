package scaloid.example

import org.scaloid.common._
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

class HelloScaloid extends SActivity {

  onCreate {
    contentView = new SVerticalLayout {
      style {
        case b: SButton => b.onClick({ 
          b.getBackground().asInstanceOf[ColorDrawable].getColor() match {
            case Color.RED => b.backgroundColor(Color.GRAY)
            case Color.GRAY => b.backgroundColor(Color.RED)
          }
        })
      }
      new SLinearLayout {
        STextView("Button: ")
        SButton(R.string.next) backgroundColor(Color.GRAY)
      }.wrap.here
    } padding 20.dip
  }

}
