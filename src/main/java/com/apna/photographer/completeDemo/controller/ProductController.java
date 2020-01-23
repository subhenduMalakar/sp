package com.apna.photographer.completeDemo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.apna.photographer.completeDemo.entity.Emails;
import com.apna.photographer.completeDemo.entity.Message;
import com.apna.photographer.completeDemo.entity.Product;
import com.apna.photographer.completeDemo.service.EmailsService;
import com.apna.photographer.completeDemo.service.MessageService;
import com.apna.photographer.completeDemo.service.ProductService;



@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private EmailsService emailsService;

	@Autowired
	private MessageService messageService;

	public ProductController(ProductService theProductService, EmailsService theEmailsService,
			MessageService theMessageService) {
		this.productService = theProductService;
		this.emailsService = theEmailsService;
		this.messageService = theMessageService;
	}

	@PostMapping("/esave")
	public String esave(@ModelAttribute("emails") Emails theEmails, Model theModel) {

		emailsService.save(theEmails);

		Emails tEmails = new Emails();

		theModel.addAttribute("emails", tEmails);

		return "prod/index";
	}

	@PostMapping("/msave")
	public String msave(@ModelAttribute("message") Message theMessage, Model theModel) {

		messageService.save(theMessage);

		Emails theEmails = new Emails();

		theModel.addAttribute("emails", theEmails);

		return "prod/index";
	}

	@GetMapping("/sendmess")
	public String sendmail(Model theModel) {

		Message theMessage = new Message();

		theModel.addAttribute("message", theMessage);

		return "prod/sendMail";
	}

	@GetMapping("/adminlkjh/list")
	public String listProduct(Model theModel) {

		List<Product> theProduct = productService.findAll();

		List<Emails> theEmails = emailsService.findAll();

		List<Message> theMessage = messageService.findAll();

		theModel.addAttribute("product", theProduct);

		theModel.addAttribute("message", theMessage);

		theModel.addAttribute("emails", theEmails);

		return "prod/list-product";
	}

	@GetMapping("/mdelete/{id}")
	public String mdelete(@PathVariable int id, Model theModel) {

		messageService.deleteById(id);

		List<Product> theProduct = productService.findAll();

		List<Emails> theEmails = emailsService.findAll();

		List<Message> theMessage = messageService.findAll();

		theModel.addAttribute("product", theProduct);

		theModel.addAttribute("message", theMessage);

		theModel.addAttribute("emails", theEmails);

		return "prod/list-product";
	}

	@GetMapping("/edelete/{id}")
	public String edelete(@PathVariable int id, Model theModel) {

		emailsService.deleteById(id);

		List<Product> theProduct = productService.findAll();

		List<Emails> theEmails = emailsService.findAll();

		List<Message> theMessage = messageService.findAll();

		theModel.addAttribute("product", theProduct);

		theModel.addAttribute("message", theMessage);

		theModel.addAttribute("emails", theEmails);

		return "prod/list-product";
	}

	@GetMapping("/pdelete/{id}")
	public String pdelete(@PathVariable int id, Model theModel) {
		
		try
        { 
            Files.deleteIfExists(Paths.get(uploadDirectory+"/"+productService.findById(id).getFimage()));
            Files.deleteIfExists(Paths.get(uploadDirectory+"/"+productService.findById(id).getSimage()));
        } 
        catch(NoSuchFileException e) 
        { 
            System.out.println("No such file/directory exists"); 
        } 
        catch(DirectoryNotEmptyException e) 
        { 
            System.out.println("Directory is not empty."); 
        } 
        catch(IOException e) 
        { 
            System.out.println("Invalid permissions."); 
        } 
		

		productService.deleteById(id);

		List<Product> theProduct = productService.findAll();

		List<Emails> theEmails = emailsService.findAll();

		List<Message> theMessage = messageService.findAll();

		theModel.addAttribute("product", theProduct);

		theModel.addAttribute("message", theMessage);

		theModel.addAttribute("emails", theEmails);

		return "prod/list-product";
	}

	@GetMapping("/adminlkjh/pform")
	public String pform(Model theModel) {

		Product theProduct = new Product();
		
		theModel.addAttribute("product", theProduct);

		return "prod/pform";
	}

	@RequestMapping("/adminlkjh/list")
	public String psave( Model theModel) {

		

		List<Product> theProduct = productService.findAll();

		List<Emails> theEmails = emailsService.findAll();

		List<Message> theMessage = messageService.findAll();

		theModel.addAttribute("product", theProduct);

		theModel.addAttribute("message", theMessage);

		theModel.addAttribute("emails", theEmails);

		return "prod/list-product";
	}
	
	
	/* file */
	
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	@PostMapping("/adminlkjh/psave")
	  public String UploadPage(@ModelAttribute("product") Product thePr,Model model) {
		
		productService.save(thePr);
		  return "uploadview";
	  }
	  @RequestMapping("/uploadfile")
	  public String upload(Model model,@RequestParam("files") MultipartFile[] files) {
		  StringBuilder fileNames = new StringBuilder();
		  
		  for (MultipartFile file : files) {
			  Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			  fileNames.append(file.getOriginalFilename()+" ");
			  try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		  model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
		  return "uploadstatusview";
	  }
	  
	  @RequestMapping("/dfile")
	  public String dfiles(Model model) {
		  
		  
		  
		  	List<Product> theProduct = productService.findAll();

			List<Emails> theEmails = emailsService.findAll();

			List<Message> theMessage = messageService.findAll();
		  
		  /* file for product*/
		  FileWriter fileWriter = null;
		  try {
		   fileWriter = new FileWriter(uploadDirectory + "/db/mesg.csv");
		   
		   fileWriter.append("Id, Name, Email, Message\n");
		   for(Message u: theMessage) {
		    fileWriter.append(String.valueOf(u.getId()));
		    fileWriter.append(",");
		    fileWriter.append(u.getUsername());
		    fileWriter.append(",");
		    fileWriter.append(u.getEmail());
		    fileWriter.append(",");
		    fileWriter.append(u.getMess());
		    fileWriter.append("\n");
		   }
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  } finally {
		   try {
		    fileWriter.flush();
		    fileWriter.close();
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		  }
		  
		  /* file for product*/
		  fileWriter = null;
		  try {
		   fileWriter = new FileWriter(uploadDirectory + "/db/prod.csv");
		   
		   fileWriter.append("Id, PName, FImage , SImage, Link ,Clicks, Price, videos \n");
		   for(Product u: theProduct) {
		    fileWriter.append(String.valueOf(u.getId()));
		    fileWriter.append(",");
		    fileWriter.append(u.getPname());
		    fileWriter.append(",");
		    fileWriter.append(u.getFimage());
		    fileWriter.append(",");
		    fileWriter.append(u.getSimage());
		    fileWriter.append(",");
		    fileWriter.append(u.getLink());
		    fileWriter.append(",");
		    fileWriter.append(String.valueOf(u.getClicks()));
		    fileWriter.append(",");
		    fileWriter.append(String.valueOf(u.getPrice()));
		    fileWriter.append(",");
		    fileWriter.append(String.valueOf(u.getVideos()));
		    fileWriter.append("\n");
		   }
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  } finally {
		   try {
		    fileWriter.flush();
		    fileWriter.close();
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		  }
		  
		  
		  /* file for product*/
		  fileWriter = null;
		  try {
		   fileWriter = new FileWriter(uploadDirectory + "/db/prod.csv");
		   
		   fileWriter.append("Id, PName, FImage , SImage, Link ,Clicks, Price, videos \n");
		   for(Product u: theProduct) {
		    fileWriter.append(String.valueOf(u.getId()));
		    fileWriter.append(",");
		    fileWriter.append(u.getPname());
		    fileWriter.append(",");
		    fileWriter.append(u.getFimage());
		    fileWriter.append(",");
		    fileWriter.append(u.getSimage());
		    fileWriter.append(",");
		    fileWriter.append(u.getLink());
		    fileWriter.append(",");
		    fileWriter.append(String.valueOf(u.getClicks()));
		    fileWriter.append(",");
		    fileWriter.append(String.valueOf(u.getPrice()));
		    fileWriter.append(",");
		    fileWriter.append(String.valueOf(u.getVideos()));
		    fileWriter.append("\n");
		   }
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  } finally {
		   try {
		    fileWriter.flush();
		    fileWriter.close();
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		  }
		  
		  
		  /* file for Emails*/
		  fileWriter = null;
		  try {
		   fileWriter = new FileWriter(uploadDirectory + "/db/Emails.csv");
		   
		   fileWriter.append("Id, PName, FImage , SImage, Link ,Clicks, Price, videos \n");
		   for(Emails u: theEmails) {
		    fileWriter.append(String.valueOf(u.getId()));
		    fileWriter.append(",");
		    fileWriter.append(u.getEmail());
		    fileWriter.append(",");
		    
		    fileWriter.append("\n");
		   }
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  } finally {
		   try {
		    fileWriter.flush();
		    fileWriter.close();
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		  }
		  


		  	File folder = new File(uploadDirectory + "/db/");
			File[] listOfFiles = folder.listFiles();
			model.addAttribute("files", listOfFiles);
			return "showFiles";
	  }
	 
	  @RequestMapping("/file/{fileName}")
		@ResponseBody
		public String show(@PathVariable("fileName") String fileName, HttpServletResponse response,Model model) {
		  		/*this url helps to download db files*/
		  
		      if (fileName.indexOf(".doc")>-1) response.setContentType("application/msword");
		      if (fileName.indexOf(".docx")>-1) response.setContentType("application/msword");
		      if (fileName.indexOf(".xls")>-1) response.setContentType("application/vnd.ms-excel");
		      if (fileName.indexOf(".csv")>-1) response.setContentType("application/vnd.ms-excel");
		      if (fileName.indexOf(".ppt")>-1) response.setContentType("application/ppt");
		      if (fileName.indexOf(".pdf")>-1) response.setContentType("application/pdf");
		      if (fileName.indexOf(".zip")>-1) response.setContentType("application/zip");
		      response.setHeader("Content-Disposition", "attachment; filename=" +fileName);
		      response.setHeader("Content-Transfer-Encoding", "binary");
		      try {
		    	  BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		    	  FileInputStream fis = new FileInputStream(uploadDirectory + "/db/"+fileName);
		    	  int len;
		    	  byte[] buf = new byte[1024];
		    	  while((len = fis.read(buf)) > 0) {
		    		  bos.write(buf,0,len);
		    	  }
		    	  bos.close();
		    	  response.flushBuffer();
		      }
		      catch(IOException e) {
		    	  e.printStackTrace();
		    	  
		      }
		      
		      
		      File folder = new File(uploadDirectory + "/db/");
				File[] listOfFiles = folder.listFiles();
				model.addAttribute("files", listOfFiles);
				return "showFiles";
		}
	
	
	
	  /* file ends*/
	
	
	
	
	
	
	
	
	
	
	
	

	/*
	 * @GetMapping("/list") public String listProduct(Model theModel) {
	 * 
	 * List<Product> theProduct = productService.findAll();
	 * 
	 * theModel.addAttribute("Product", theProduct);
	 * 
	 * return "employees/list-Product"; }
	 * 
	 * @GetMapping("/showFormForAdd") public String showFormForAdd(Model theModel) {
	 * 
	 * Product theProduct = new Product();
	 * 
	 * theModel.addAttribute("Product", theProduct);
	 * 
	 * return "/ap/ProductRegForm"; }
	 * 
	 * @PostMapping("/save") public String saveProduct(@ModelAttribute("Product")
	 * Product theProduct) {
	 * 
	 * productService.save(theProduct);
	 * 
	 * return "/ap/ProductConformationPage"; }
	 * 
	 * @GetMapping("/delete/{id}") public String deleteProduct(@PathVariable int id)
	 * {
	 * 
	 * productService.deleteById(id);
	 * 
	 * return "redirect:/Product/list"; }
	 */
}
