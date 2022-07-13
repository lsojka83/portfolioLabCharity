document.addEventListener("DOMContentLoaded", function() {

  const donForm = document.getElementById("donForm");
  const quantityOutput = document.getElementById("quantityOutput");
  const institutionOut = document.getElementById("institutionOut");
  const streetOut = document.getElementById("streetOut");
  const cityOut = document.getElementById("cityOut");
  const zipCodeOut = document.getElementById("zipCodeOut");
  const phoneNumberOut = document.getElementById("phoneNumberOut");
  const pickUpDateOut = document.getElementById("pickUpDateOut");
  const pickUpTimeOut = document.getElementById("pickUpTimeOut");
  const moreInfoOut = document.getElementById("moreInfoOut");
  const institutionListDiv = document.getElementById("institution-list");

  // const paginationDiv = document.getElementById("pagination");
  const baseUrl = 'http://localhost:8080/institutions/';
  let institutionCurrentPage = 0;

  const institutionPreviousPageLink = document.getElementById('institutionPreviousPageLink');
  const institutionNextPageLink = document.getElementById('institutionNextPageLink');

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();


          this.currentStep++;
          this.updateForm();


        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // Getting data from inputs and show them in summary
      const selectedCategoryArray = [];
      const categoryCheckboxes = donForm.elements.categories;
      for (let i = 0; i < categoryCheckboxes.length; i++)
      {
        if(categoryCheckboxes[i].checked) {
          selectedCategoryArray.push(categoryCheckboxes[i].id)
        }
      }

      let selectedInstitution = null;
      if(donForm.elements.institution!=null) {
        let institutions = donForm.elements.institution;
        for (let i = 0; i < institutions.length; i++) {
          if (institutions[i].checked) {
            selectedInstitution = institutions[i].title;
            // console.log(selectedInstitution);
          }
        }
      }

      quantityOutput.innerHTML = donForm.elements.quantity.value + "x worek z categoriami:" + selectedCategoryArray;
      if(selectedInstitution!=null) {
        institutionOut.innerText = "Dla fundacji: " + selectedInstitution;
      }
      streetOut.innerText = donForm.elements.street.value;
      cityOut.innerText = donForm.elements.city.value;
      zipCodeOut.innerText = donForm.elements.zipCode.value;
      phoneNumberOut.innerText = donForm.elements.phoneNumber.value;
      pickUpDateOut.innerText = donForm.elements.pickUpDate.value;
      pickUpTimeOut.innerText = donForm.elements.pickUpTime.value;
      moreInfoOut.innerText = donForm.elements.pickUpComment.value;
    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }


  function createInstituitonDiv(institution)
  {
    const div = document.createElement("div");
    div.className = "form-group form-group--checkbox";
    const label = document.createElement("label");
    const input = document.createElement("input");
    input.type = "radio";
    input.name = "institution";
    input.title = institution.name;
    input.value = institution.id;
    label.appendChild(input);
    const spanCheckbox = document.createElement("span");
    spanCheckbox.className = "checkbox radio";
    label.appendChild(spanCheckbox);
    const spanDescription = document.createElement("span");
    spanDescription.className = "description";
    const divTitle = document.createElement("div");
    divTitle.className = "title";
    divTitle.innerHTML = institution.name;
    spanDescription.appendChild(divTitle);
    const divSubTitle = document.createElement("div");
    divSubTitle.className = "subtitle";
    divSubTitle.innerHTML = institution.description;
    spanDescription.appendChild(divSubTitle);
    label.appendChild(spanDescription);
    div.appendChild(label);
    return div;
  }

  const addInstitutionsToInstitutionListDiv = (institutions) =>
  {
    institutions.forEach((institution) =>{
      const institutionDiv = createInstituitonDiv(institution);
      institutionListDiv.appendChild(institutionDiv);
    })
  }

  function getUrl(institutionPage)
  {
    return baseUrl+institutionPage;
  }

  const fetchInstitutionPage = (institutionPage)=>{
    fetch(getUrl(institutionPage))
        .then((response)=>
        {
          return response.json()
        })
        .then((institutions)=>
        {
          addInstitutionsToInstitutionListDiv(institutions);
          // institutions.forEach((i)=>console.log(i))
        })
      }

  fetchInstitutionPage(institutionCurrentPage);

  // const institutionPagesLinksList = [];

  function createPageLink(number)
  {
    const link = document.createElement("a");
    link.href= "#";
    link.id="institutionPageLink"+number;
    link.title = number+1;
    link.innerHTML = number+1+"&nbsp";
    link.className="institutionPageLink";
    if(institutionPreviousPageLink!=null) {
      institutionPreviousPageLink.parentElement.insertBefore(link, institutionNextPageLink);
    }
    // institutionPagesLinksList[number] = link;
    link.addEventListener("click", loadPageData);
  }
  let institutionPageCount = 0;

  function getUrlForPageCount()
  {
    return baseUrl+"pages";
  }
  //add inst. page links
  const fetchPageCount = ()=>{
    fetch(getUrlForPageCount())
        .then((response)=>
        {
          return response.json();
        })
        .then((response)=>
        {
          institutionPageCount = response;
          addLinks();
          institutionPreviousPageLink.addEventListener("click", previousPageLinkClinkHandler);
          institutionNextPageLink.addEventListener("click", nextPageLinkClinkHandler)
        })
  }
  fetchPageCount();

  //add inst. page links
  const addLinks = () => {
    for (let i = 0; i < institutionPageCount; i++) {
      createPageLink(i);
    }
  }
  //handling inst. pagination link in form view
  function previousPageLinkClinkHandler(event) {
    event.preventDefault();
    if(institutionCurrentPage > 0) {
      institutionListDiv.innerHTML = "";
      institutionCurrentPage--;
      fetchInstitutionPage(institutionCurrentPage);
    }
    // console.log(institutionCurrentPage);
  }

  function nextPageLinkClinkHandler() {
    event.preventDefault();
    if(institutionCurrentPage < institutionPageCount-1) {
      institutionListDiv.innerHTML = "";
      institutionCurrentPage++;
      fetchInstitutionPage(institutionCurrentPage);
    }
    // console.log(institutionCurrentPage);
  }

  function loadPageData(event)
  {
    event.preventDefault();
    institutionListDiv.innerHTML = "";
    fetchInstitutionPage(event.target.title-1);
    institutionCurrentPage = event.target.title-1;
    // console.log(institutionCurrentPage);
  }
});


