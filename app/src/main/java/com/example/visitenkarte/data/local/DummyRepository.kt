package com.example.visitenkarte.data.local

import com.example.visitenkarte.model.Author
import com.example.visitenkarte.model.Book
import com.example.visitenkarte.model.Publisher
import com.example.visitenkarte.model.ReadingStatus
import java.util.Date

class DummyRepository {

    private val myBookList = mutableListOf(
        //Book(frontCoverPath = "star-wars-schleier-der-taeuschung.png"),
        Book(
            isbn13 = "9783442366095",
            title = "Star Wars Dark Lord The Rise of Darth Vader",
            publisher = Publisher.BENVELET.toString(),
            numPages = 643,
            originalReleaseDate = Date(),
            frontCoverPath = "star-wars-dunkler-lord.jpeg",
            blurbText = "The Text from the Back Cover of The Book",
            author = Author("James", "Luceno", "01-04-1947"),
            readingStatus = ReadingStatus.FINISHED
        ),
        Book(
            isbn13 = "9783734160615",
            title = "Star Wars Tarkin",
            publisher = Publisher.BENVELET.toString(),
            numPages = 643,
            originalReleaseDate = Date(),
            frontCoverPath = "star-wars-tarkin.jpg",
            blurbText = "The Text from the Back Cover of The Book",
            author = Author("James", "Luceno", "01-04-1947"),
            readingStatus = ReadingStatus.FINISHED
        ),
        Book(
            isbn13 = "9783442380459",
            title = "Star Wars Darth Plagueis",
            publisher = Publisher.BENVELET.toString(),
            numPages = 756,
            originalReleaseDate = Date(),
            frontCoverPath = "star-wars-darth-plagueis.jpeg",
            blurbText = "asdf",
            author = Author("James", "Luceno", "01-04-1947"),
            readingStatus = ReadingStatus.FINISHED
        ),
        Book(
            isbn13 = "9780753553916",
            title = "Think Again",
            publisher = Publisher.PENGUIN.toString(),
            numPages = 308,
            originalReleaseDate = Date(),
            frontCoverPath = "think-again-adam-grant.webp",
            blurbText = "Discover how rethinking can lead to excellence at work and wisdom in life",
            author = Author("Adam","Grant","13-08-1981"),
            readingStatus = ReadingStatus.CURRENT
        ),
        Book(
            isbn13 = "9783734162237",
            title = "Star Wars Meister und Schüler",
            publisher = Publisher.BENVELET.toString(),
            author = Author(firstName = "Claudia", lastName = "Gray", birthdate = "01-01-1970"),
            numPages = 448,
            originalReleaseDate = Date(),
            frontCoverPath = "star-wars-meister-und-schueler.png",
            blurbText = "»Ich habe da ein ganz mieses Gefühl.« Obi-Wan Kenobi zu Qui-Gon Jinn\n" +
                    "\n" +
                    "Ein Jedi muss ein furchtloser Krieger sein, ein Hüter der Gerechtigkeit und ein Gelehrter in den Wegen der Macht. Aber die wichtigste Aufgabe eines Jedi ist es, das Erlernte weiterzugeben. Und so ist es für Qui-Gon Jinn, einem Veteranen unzähliger Schlachten, auch die größte Angst, bei seinem Schüler Obi-Wan Kenobi zu versagen. Obi-Wan hingegen erfährt, dass sein Meister sich möglicherweise von ihm trennen will, und die einzige Erklärung, die ihm einfällt, ist sein eigenes Versagen. Dann brechen sie zu ihrer möglicherweise letzten gemeinsamen Mission auf - die sie nur erfolgreich bewältigen können, wenn sie einander blind vertrauen ..."
        )
    )
    fun getAllBooks():List<Book> = myBookList
    fun getBookByISBN(isbn:String):Book? = getAllBooks().find { it.isbn13 == isbn }

    fun addBook(book:Book) = myBookList.add(book)

//    suspend fun loadBookCoverImage(book: Book?): Bitmap? = withContext(Dispatchers.IO) {
//        val coverPath = book?.frontCoverPath
//        if (coverPath.isNullOrEmpty()) return@withContext null
//
//        val coverFolder = File(appContext.filesDir, "frontcovers")
//        if (!coverFolder.isDirectory) return@withContext null
//
//        val coverArt = File(coverFolder, coverPath)
//        if (coverArt.exists()) {
//            BitmapFactory.decodeFile(coverArt.absolutePath)
//        } else {
//            null
//        }
//    }
}