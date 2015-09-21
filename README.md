**Markdown**<br>
**REST service that takes markdown-style text as a POST request body, and returns HTML.**
<br>**Backlog**
- ` Lines starting with #, ##, ### etc. become headers <h1/>, <h2/>, <h3/> etc.`
-  `Simple lines become paragraphs (just a line – > <p>just a line</p>)`
-  `Text wrapped in * … * becomes emphasized (*lorem* – > <em>lorem</em>`
- `Text wrapped in ** … ** becomes strong (**lorem** – > <strong>lorem</strong>)
- `Links support ([example link]( http://example.com/ ) – > <a href= “ http://example.com/ ” >example link</a>)`
- `Other than that, no transformation should happen to text`

<br>**Extra credit**<br>
- Application is deployed somewhere (heroku, jelastic etc.)
- Authorization
- Stats are saved into mongodb (e.g. who converted what when)

<br>**Example**<br>
INPUT:<br>
`# Lorem ipsum`<br>
`Dolor sit amet,`<br>
`consetetur *sadipscing* elitr,`<br>
`sed [diam](http://mysite.com) nonumy eirmod tempor`<br>
<br>**OUTPUT:**<br>
`<html>`<br>
`<body>`<br>
`<h1>Lorem ipsum</h1>`<br>
`<p>Dolor sit amet,</p>`<br>
`<p>consetetur <em>sadipscing</em> elitr,</p>`<br>
`<p>sed <a href=”http://mysite.com”>diam</a> nonumy eirmod tempor</p>`<br>
`</body>`<br>
`</html>`<br>

<br>**Deliverables**<br>
- Source code in a zip archive
- (extra credit) Deployed application URL
